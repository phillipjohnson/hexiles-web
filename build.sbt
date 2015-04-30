enablePlugins(ScalaJSPlugin)

name := "hexiles-web"

version := "1.0"

scalaVersion := "2.11.6"

scalaJSStage in Global := FastOptStage

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.1"

testFrameworks += new TestFramework("utest.runner.Framework")