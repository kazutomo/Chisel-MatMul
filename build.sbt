// This is based on build.sbt in the chisel-template reposiory

ThisBuild / scalaVersion     := "2.13.15"
ThisBuild / version          := "0.2.0"

val chiselVersion = "7.6.0"

lazy val root = (project in file("."))
  .settings(
    name := "ChiselMatmul",
    libraryDependencies ++= Seq(
      "org.chipsalliance" %% "chisel" % chiselVersion,
      "org.scalatest" %% "scalatest" % "3.2.16" % "test",
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
