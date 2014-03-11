package models

import scala.slick.driver.H2Driver.simple._
import scala.slick.lifted.TableQuery
import play.api.db.DB
import play.api.Play.current
import play.api.Logger

object TablesGenerator extends DomainComponent {

  def createSchema(): Unit = {
    try {
      DBConfig.db.withSession { implicit session =>
        (Suppliers.ddl).create
        createSuppliers
        Logger.info("All tables have been created")
      }
    } catch {
      case exception: Exception => Logger.warn("Error in table creation" + exception.getMessage())
    }
  }

  def createSuppliers =
    DBConfig.db.withSession { implicit session =>
      // Insert some suppliers
      val lists = List(
        Supplier(1, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
        Supplier(2, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
        Supplier(3, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"),
        Supplier(4, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
        Supplier(5, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
        Supplier(6, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"),
        Supplier(7, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
        Supplier(8, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
        Supplier(9, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"),
        Supplier(10, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
        Supplier(11, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
        Supplier(12, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"),
        Supplier(13, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
        Supplier(14, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
        Supplier(15, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"))

      lists.foreach(Suppliers.insert(_))
    }
}