ThisBuild / autoStartServer := false

resolvers ++= Resolver.sonatypeOssRepos("public")

// The std library for sbt is handled by sbt itself so no need to include it in the report.
dependencyUpdatesFilter -= moduleFilter(name = "scala-library")

update / evictionWarningOptions := EvictionWarningOptions.empty

addDependencyTreePlugin

addSbtPlugin("com.mayreh" % "sbt-thank-you-stars" % "0.2")
addSbtPlugin("com.timushev.sbt" % "sbt-rewarn" % "0.1.3")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.6.4")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.10.0")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.0")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "1.2.0")
addSbtPlugin("com.github.sbt" % "sbt-native-packager" % "1.9.16")
// Zio helpers
addSbtPlugin("dev.zio" % "zio-sbt-ecosystem" % "0.4.0-alpha.8")
addSbtPlugin("dev.zio" % "zio-sbt-ci" % "0.4.0-alpha.8")
//addSbtPlugin("dev.zio" % "zio-sbt-website" % "0.4.0-alpha.8")
