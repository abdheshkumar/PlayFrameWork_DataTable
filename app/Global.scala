import play.api._
import akka.event.slf4j.Logger
import models.TablesGenerator

object Global extends GlobalSettings {

  override def onStart(app: Application): Unit = {
    Logger("*********************************Application has started***********************************************")
    TablesGenerator.createSchema
    Logger("Application has started")
  }

  override def onStop(app: Application): Unit = {
    Logger("Application shutdown...")
  }
}