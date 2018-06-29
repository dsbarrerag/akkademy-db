import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.davsanba",
      scalaVersion := "2.12.5",
      version      := "0.0.1-SNAPSHOT"
    )),
    name := "akkademy-db",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % "2.5.13",
      "com.typesafe.akka" %% "akka-remote" % "2.5.13",
      "com.typesafe.akka" %% "akka-testkit" % "2.5.13" % Test,
      scalaTest % Test
    )
  )

mappings in (Compile, packageBin) ~= { _.filterNot { case (_, name) =>
  Seq("conf/application.conf").contains(name)
}}
