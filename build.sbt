import Dependencies._

ThisBuild / organization := "com.fayi"
ThisBuild / scalaVersion := "3.2.2"

ThisBuild / scalacOptions ++=
  Seq(
    "-deprecation",
    "-explain",
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
    "-Yexplicit-nulls",
    "-Ykind-projector",
    "-Ysafe-init",
  ) ++ Seq("-rewrite", "-indent") ++ Seq("-source", "future-migration")

lazy val `zio-mono` =
  project
    .in(file("."))
    .settings(name := "zio-mono")
    .settings(commonSettings)
    .settings(dependencies)
    .settings(
      Compile / mainClass := Some("com.fayi.ziomono.HelloApp")
    )
    .enablePlugins(JavaAppPackaging)
    .enablePlugins(
      ZioSbtEcosystemPlugin,
      ZioSbtCiPlugin,
    )

lazy val commonSettings = {
  lazy val commonScalacOptions = Seq(
    Compile / console / scalacOptions --= Seq(
      "-Wunused:_",
      "-Xfatal-warnings",
    ),
    Test / console / scalacOptions :=
      (Compile / console / scalacOptions).value,
  )

  lazy val otherCommonSettings = Seq(
    update / evictionWarningOptions := EvictionWarningOptions.empty
  )

  Seq(
    commonScalacOptions,
    otherCommonSettings,
  ).reduceLeft(_ ++ _)
}

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    // main dependencies
    "dev.zio" %% "zio" % "2.0.13",
    "dev.zio" %% "zio-http" % "3.0.0-RC1",
    "dev.zio" %% "zio-json" % "0.5.0",
    "dev.zio" %% "zio-prelude" % "1.0.0-RC19",
  ),
  libraryDependencies ++= Seq(
    com.eed3si9n.expecty.expecty,
    org.scalacheck.scalacheck,
    org.scalameta.`munit-scalacheck`,
    org.scalameta.munit,
    org.typelevel.`discipline-munit`,
  ).map(_ % Test),
)
