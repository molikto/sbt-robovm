lazy val roboVMVersion = settingKey[String]("RoboVM Version against which this plugin is built")

lazy val sbtRoboVM = (project in file(".")).
  enablePlugins(BuildInfoPlugin).
  settings(
    name := "sbt-robovm",
    roboVMVersion := "2.3.0",
    licenses += ("BSD 3-Clause", url("http://opensource.org/licenses/BSD-3-Clause")),
    organization := "org.roboscala",
    version := roboVMVersion.value,
    sbtPlugin := true,
    publishMavenStyle := false,
    bintrayOrganization := None,
    bintrayRepository := "sbt-plugins",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xcheckinit", "-Xfatal-warnings"),
    javacOptions ++= Seq("-source", "6", "-target", "6"),
    resolvers ++= {if (roboVMVersion.value.contains("-SNAPSHOT")) Seq(Resolver.sonatypeRepo("snapshots")) else Seq()},
    libraryDependencies += "com.mobidevelop.robovm" % "robovm-dist-compiler" % roboVMVersion.value,
    libraryDependencies += "org.robovm" % "robovm-maven-resolver" % "1.8.0",
    libraryDependencies += "com.mobidevelop.robovm" % "robovm-templater" % roboVMVersion.value,
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, roboVMVersion),
    buildInfoPackage := "sbtrobovm"
  )
