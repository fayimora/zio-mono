import Dependencies._

ThisBuild / organization := "com.fayi"
ThisBuild / scalaVersion := "3.2.2"
ThisBuild / name := "zio-mono-root"

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

lazy val root =
  project
    .in(file("."))
    .settings(name := "zio-mono-root")
    .aggregate(api, worker1, worker2)

lazy val api =
  project
    .in(file("apps/api"))
    .settings(name := "api")
    .settings(commonSettings)
    .settings(dependencies)
    .settings(
      Compile / mainClass := Some("com.fayi.ziomono.api.ApiApp")
    )
    .enablePlugins(commonPlugins *)

lazy val worker1 = project
  .in(file("apps/worker1"))
  .settings(name := "worker1")
  .settings(commonSettings)
  .settings(dependencies)
  .settings(
    Compile / mainClass := Some("com.fayi.ziomono.worker1.Worker1App")
  )
  .enablePlugins(commonPlugins *)

lazy val worker2 = project
  .in(file("apps/worker2"))
  .settings(name := "worker2")
  .settings(commonSettings)
  .settings(dependencies)
  .settings(
    Compile / mainClass := Some("com.fayi.ziomono.worker2.Worker2App")
  )
  .enablePlugins(commonPlugins *)

lazy val commonPlugins = Seq(JavaAppPackaging, ZioSbtEcosystemPlugin /*, ZioSbtCiPlugin*/ )

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
