package models

import scala.slick.driver.H2Driver.simple._
import play.api.Play
import play.api.db.DB
import play.api.Play.current

object DBConfig {
  lazy val db = Database.forDataSource(DB.getDataSource())
}