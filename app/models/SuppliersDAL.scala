package models
import scala.slick.driver.H2Driver.simple._
import scala.slick.lifted.TableQuery
import play.api.libs.json.JsValue
import scala.slick.jdbc.meta.MTable
import scala.slick.jdbc.meta._

trait SuppliersMetaData {
  def insertSupplier(supplier: Supplier): Int
  def updateSupplier(supplier: Supplier): Int
  def deleteSupplier(supplierId: Int): Int
  def getSuppliers: List[Supplier]

}

class SuppliersDAL extends SuppliersMetaData with DomainComponent {

  def insertSupplier(supplier: Supplier): Int = {
    DBConfig.db.withSession { implicit session =>
      Suppliers.insert(supplier)
    }
  }

  def updateSupplier(supplier: Supplier): Int = DBConfig.db.withSession { implicit session =>
    Suppliers.filter { f => f.id === supplier.id }.update(supplier)
  }

  def deleteSupplier(supplierId: Int): Int = DBConfig.db.withSession { implicit session =>
    Suppliers.filter { f => f.id === supplierId }.delete
  }

  def getSuppliers: List[Supplier] = DBConfig.db.withSession { implicit session =>
    Suppliers.list
  }

  def getSuppliersByKeyWord(params: DataTableParam): List[Supplier] = DBConfig.db.withSession { implicit session =>
    params.sSearch.isEmpty match {
      case true => SuppliersDAL.getSuppliers.drop(params.iDisplayStart).take(params.iDisplayLength)
      case false => (for (s <- Suppliers if (s.name.startsWith(params.sSearch))) yield (s)).list
    }
  }
}

case class DataTableParam(sSearch: String, iDisplayStart: Int, iDisplayLength: Int, iSortCol: Int, sSortDir: String)
object SuppliersDAL extends SuppliersDAL
