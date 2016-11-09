name := "activator-play-slick"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.5" // or "2.10.4"

resolvers ++= Seq("RoundEights" at "http://maven.spikemark.net/roundeights")

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "com.typesafe.play" %% "play-slick" % "0.8.1",
  "com.roundeights" %% "hasher" % "1.2.0"
)


fork in Test := false

lazy val root = (project in file(".")).enablePlugins(PlayScala)