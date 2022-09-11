// This is based on build.sbt in the chisel-template reposiory

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.0.3"

val chiselVersion = "3.5.4"

lazy val root = (project in file("."))
  .settings(
    name := "simple-systolic-matmul",
    libraryDependencies ++= Seq(
      "edu.berkeley.cs" %% "chisel3" % chiselVersion,
      "edu.berkeley.cs" %% "chiseltest" % "0.5.4" % "test"
    ),
    scalacOptions ++= Seq(
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit",
      "-P:chiselplugin:genBundleElements",
    ),
    addCompilerPlugin("edu.berkeley.cs" % "chisel3-plugin" % chiselVersion cross CrossVersion.full),
  )
