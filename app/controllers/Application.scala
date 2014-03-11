package controllers

import models._
import play.api.libs.json.JsObject
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller
import scala.slick.lifted.TableQuery
import scala.slick.lifted.Query
import scala.slick.driver.PostgresDriver.simple._
import play.api.Logger
object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def list = Action { implicit request =>

    val params = DataTableParam(
      request.getQueryString("sSearch").getOrElse(""),
      request.getQueryString("iDisplayStart").getOrElse("0").toInt,
      request.getQueryString("iDisplayLength").getOrElse("10").toInt,
      request.getQueryString("iSortCol_0").getOrElse("1").toInt,
      request.getQueryString("sSortDir_0").getOrElse("asc"))

    val totalSuppliers = SuppliersDAL.getSuppliers.length

    val suppliers =SuppliersDAL.getSuppliersByKeyWord(params)
    Logger.info(":::::::::" + params.sSearch)

    val suppliersOrderBy = (params.iSortCol, params.sSortDir) match {
      case (1, "asc") => suppliers.sortBy(_.name)
      case (1, "desc") => suppliers.sortWith(_.name > _.name)
      case (2, "asc") => suppliers.sortBy(_.street)
      case (2, "desc") => suppliers.sortWith(_.street > _.street)
      case (5, "asc") => suppliers.sortBy(_.zip)
      case (5, "desc") => suppliers.sortWith(_.zip > _.zip)
      case (_, _) => suppliers.sortBy(_.name)
    }

    val jsonObject = Json.toJson(Map("aaData" -> suppliersOrderBy.map(_.toJson)))

    val dataTableJson = Json.toJson(Map("iTotalRecords" -> totalSuppliers, "iTotalDisplayRecords" -> totalSuppliers))
      .as[JsObject].deepMerge(jsonObject.as[JsObject])

    Ok(dataTableJson)
  }

}