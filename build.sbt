
sbtPlugin := true

organization := "atd"

name := "sbt-liquibase"

version := "0.2"

libraryDependencies += "org.liquibase" % "liquibase-core" % "2.0.2"

publishTo := Some(Resolver.file("bigtoast.github.com", file(Path.userHome + "/Projects/Destroyer/bigtoast.github.com/repo")))
