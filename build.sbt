val ScalaVersion = "2.11.8"

lazy val commonSettings = Seq(
  organization := "com.lespea",
  scalaVersion := ScalaVersion,
  scalacOptions ++= Seq(
    "-encoding", "utf8"
  )
)


lazy val root = (project in file("."))
  .settings()
  .settings(
    publishArtifact := false,
    publishLocal := { },
    publish      := { }
  )
  .aggregate(externalJarMaker, jarLinker, testApp)


lazy val externalJarMaker = (project in file("externalJarMaker"))
  .settings(
    commonSettings,
    name    := "externalJarMaker",
    version := "0.0.1"
  )


lazy val jarLinker = (project in file("jarLinker"))
  .settings(
    commonSettings,
    name    := "jarLinker",
    version := "0.0.1",

    // Magic to "fake-publish" a jar with sbt
    autoScalaLibrary  := false,  //don't attach scala libs as dependencies
    crossPaths        := false,  //don't add scala version to this artifacts in repo
    exportJars        := true,
    publishMavenStyle := true,

    packageBin in Compile := baseDirectory.value / "",
    packageDoc in Compile := baseDirectory.value / "",
    packageSrc in Compile := baseDirectory.value / ""
  )


lazy val testApp = (project in file("testApp"))
  .settings(
    commonSettings,
    name    := "testApp",
    version := "0.0.1"
  ).dependsOn(jarLinker)
