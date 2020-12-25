name := "zapl"

version := "0.1"

scalaVersion := "2.13.4"

idePackagePrefix := Some("io.zana.zapl")

libraryDependencies +=
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"

libraryDependencies +=
  "com.novocode" % "junit-interface" % "0.8" % "test->default"
