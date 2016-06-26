name := """ReseauProfessionnel"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.38",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.apache.directory.studio" % "org.apache.commons.io" % "2.4",
  filters,
  cache,
  javaWs
)
routesGenerator := InjectedRoutesGenerator

javaOptions in Test ++= Seq(
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9998",
  "-Xms512M",
  "-Xmx1536M",
  "-Xss1M",
  "-XX:MaxPermSize=384M"
)