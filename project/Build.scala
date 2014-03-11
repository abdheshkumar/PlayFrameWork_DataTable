import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "DataTable"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    anorm,
    jdbc,
    "com.typesafe.slick" %% "slick" % "2.0.0",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "postgresql" % "postgresql" % "9.1-901.jdbc4",
    "com.h2database" % "h2" % "1.3.166",
    "mysql" % "mysql-connector-java" % "5.1.23")

  val main = play.Project(appName, appVersion, appDependencies).settings( // Add your own project settings here      
  )

}
