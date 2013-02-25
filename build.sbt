sbtPlugin := true

organization := "com.github.bigtoast"

name := "sbt-liquibase"

version := "0.5.1"

crossScalaVersions := Seq("2.9.2", "2.10.0")

libraryDependencies += "org.liquibase" % "liquibase-core" % "2.0.5"


publishTo := Some(Resolver.file("bigtoast.github.com", file(Path.userHome + "/Projects/BigToast/bigtoast.github.com/repo")))

ScriptedPlugin.scriptedSettings
