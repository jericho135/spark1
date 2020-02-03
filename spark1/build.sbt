name := "json-reader_blinov"

version := "0.1"

exportJars := true

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" % "spark-core_2.12" % "2.4.4" % "provided"


libraryDependencies += "org.json4s" % "json4s-jackson_2.12" % "3.7.0-M1"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}