name := """fokus-server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1"
)

libraryDependencies += "com.h2database" % "h2" % "1.4.191"
libraryDependencies +=  "com.typesafe.slick" %% "slick" % "3.1.1"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "2.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
