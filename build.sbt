val dottyVersion = "3.0.0-M3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-simple",
    version := "0.1.0",

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" %
      "junit-interface" % "0.11" % "test",
      
    libraryDependencies +=
      "org.scala-lang.modules" %%
        "scala-parser-combinators" % "1.2.0-M1" 
  )
