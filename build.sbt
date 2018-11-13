organization := "com.edmondcote"

name := "uvmgen"

version := "1.0"

scalaVersion := "2.12.6"

scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "3.7.0",
  "io.circe" %% "circe-yaml" % "0.9.0",
  "io.circe" %% "circe-core" % "0.10.1",
  "io.circe" %% "circe-generic" % "0.10.1",
  "io.circe" %% "circe-parser" % "0.10.1",
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
)
