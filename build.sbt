// This is based on build.sbt in the chisel-template reposiory

ThisBuild / scalaVersion     := "2.13.15"
ThisBuild / version          := "0.1.0"

val chiselVersion = "6.7.0"

lazy val root = (project in file("."))
  .settings(
    name := "ChiselMatmul",
    libraryDependencies ++= Seq(
      "org.chipsalliance" %% "chisel" % chiselVersion,
      "org.scalatest" %% "scalatest" % "3.2.16" % "test",
      "edu.berkeley.cs" %% "chiseltest" % "6.0.0",
    ),
    scalacOptions ++= Seq(
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit",
      "-Ymacro-annotations",
    ),
    addCompilerPlugin("org.chipsalliance" % "chisel-plugin" % chiselVersion cross CrossVersion.full),

  )
