// This is based on build.sbt in the chisel-template reposiory

//ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / scalaVersion     := "2.12.17"
ThisBuild / version          := "0.0.3"

//val chiselVersion = "3.5.4"
//val chiseltestVersion = "0.5.4"
val chiselVersion = "3.6-SNAPSHOT"
val chiseltestVersion = "0.6-SNAPSHOT"


lazy val root = (project in file("."))
  .settings(
    name := "simple-systolic-matmul",
    libraryDependencies ++= Seq(
      "edu.berkeley.cs" %% "chisel3" % chiselVersion,
      "edu.berkeley.cs" %% "chiseltest" % chiseltestVersion % "test"
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
