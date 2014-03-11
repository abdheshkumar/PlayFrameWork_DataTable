package models

import scala.slick.driver.H2Driver.simple._
import scala.slick.lifted.{ ProvenShape, ForeignKeyQuery }
import play.api.libs.json.JsValue
import play.api.libs.json.Json

// A Suppliers table with 6 columns: id, name, street, city, state, zip
trait DomainComponent {
  val Suppliers: TableQuery[Suppliers] = TableQuery[Suppliers]
  class Suppliers(tag: Tag) extends Table[Supplier](tag, "SUPPLIERS") {
    def id: Column[Int] = column[Int]("SUP_ID", O.PrimaryKey) // This is the primary key column
    def name: Column[String] = column[String]("SUP_NAME")
    def street: Column[String] = column[String]("STREET")
    def city: Column[String] = column[String]("CITY")
    def state: Column[String] = column[String]("STATE")
    def zip: Column[String] = column[String]("ZIP")

    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, name, street, city, state, zip) <> (Supplier.tupled, Supplier.unapply)
  }
}
case class Supplier(id: Int = 0, name: String, street: String, city: String, state: String, zip: String) {
  def toJson: JsValue = Json.toJson(
    Map(
      "0" -> Json.toJson(id),
      "1" -> Json.toJson(name),
      "2" -> Json.toJson(street),
      "3" -> Json.toJson(city),
      "4" -> Json.toJson(state),
      "5" -> Json.toJson(zip)))

}