// Name is a prefix in the object code filename.
name := "Hexiles web"
scalaVersion := "2.11.8"

// Build file for example applications written in ScalaJS using scala-js-dom and scala-js-workbench
// lazy val root = (project in file(".")).
enablePlugins(ScalaJSPlugin)
workbenchSettings

// Optional, necessary to sbt run, needs phantomJS to be installed.
jsDependencies += RuntimeDOM
scalaJSStage in Global := FastOptStage

libraryDependencies ++= Seq(
  "be.doeraene" %%% "scalajs-jquery" % "0.9.0",
  "com.lihaoyi" %%% "scalatags" % "0.6.0",
  "com.lihaoyi" %%% "utest" % "0.4.3" % "test",
 "org.scala-js" %%% "scalajs-dom" % "0.9.1")
skip in packageJSDependencies := false // All JavaScript dependencies to be concatenated to a single file

// Update without refreshing the page every time fastOptJS completes
updateBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)

testFrameworks += new TestFramework("utest.runner.Framework")

// Workbench has to know how to restart your application.
bootSnippet := "com.letstalkdata.hexiles.HexilesApp().main();"
