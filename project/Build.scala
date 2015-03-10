import com.typesafe.sbt.less.Import.LessKeys
import com.typesafe.sbt.web.Import._
import com.typesafe.sbt.web.SbtWeb
import sbt.Keys._
import sbt._

object ScalaTestTutorial extends Build {

  val appName     = "scalatest-tutorial"

  val appVersion  = "1.0"

  val scalazVersion = "7.0.6"
  val playVersion = "2.3.8"
  val akkaVersion = "2.3.8"

  scalacOptions ++= Seq("-feature")

  val sharedSettings: Seq[Def.Setting[_]] = Seq(
    organization := "com.torfox",
    version := appVersion,
    scalaVersion := "2.11.6",
    resolvers ++= Seq(
      "Typesafe repository snapshots"    at "http://repo.typesafe.com/typesafe/snapshots/",
      "Typesafe repository releases"     at "http://repo.typesafe.com/typesafe/releases/",
      "Sonatype releases"                at "https://oss.sonatype.org/content/repositories/releases",
      "Sonatype snapshots"               at "https://oss.sonatype.org/content/repositories/snapshots",
      "Sonatype repo"                    at "https://oss.sonatype.org/content/groups/scala-tools/",
      "Sonatype staging"                 at "http://oss.sonatype.org/content/repositories/staging",
      "spray repo"                       at "http://repo.spray.io",
      "Java.net Maven2 Repository"       at "http://download.java.net/maven/2/",
      "Twitter Repository"               at "http://maven.twttr.com",
      "Websudos releases"                at "http://maven.websudos.co.uk/ext-release-local"
    ),
    scalacOptions ++= Seq(
      "-language:postfixOps",
      "-language:implicitConversions",
      "-language:reflectiveCalls",
      "-language:higherKinds",
      "-language:existentials",
      "-Yinline-warnings",
      "-Xlint",
      "-deprecation",
      "-feature",
      "-unchecked"
    ),
    libraryDependencies ++= Seq(
      "com.typesafe.scala-logging"  %% "scala-logging"          % "3.1.0",
      "org.scalatest"               %% "scalatest"              % "2.2.4"  % "test",
      "org.scalacheck"              %% "scalacheck"             % "1.12.2" % "test",
      "org.scalamock"               %% "scalamock-scalatest-support" % "3.2.1" % "test"
    )
  )

  lazy val root = Project(
    id = "scalatest-tutorial",
    base = file("."),
    settings = Defaults.coreDefaultSettings ++ sharedSettings
  ).settings(
    name := "main"
  ).aggregate(
    uiPiece,
    common
  )


  lazy val common = Project(
    id = "common",
    base = file("common"),
    settings = Defaults.coreDefaultSettings ++ sharedSettings
  ).settings(
      name := "common",
      libraryDependencies ++= Seq(
        "com.typesafe.akka"           %% "akka-actor"             % akkaVersion,
        "com.typesafe.akka"           %% "akka-slf4j"             % akkaVersion,
        "com.github.nscala-time"      %% "nscala-time"            % "1.0.0",
        "org.scalatest"               %% "scalatest"              % "2.2.4"  % "test",
        "org.scalatestplus"           %% "play"                   % "1.2.0"  % "test"
      )
    )

  lazy val uiPiece = Project(
    id = "ui-piece",
    base = file("ui-piece"),
    settings = Defaults.coreDefaultSettings ++ sharedSettings
  ).enablePlugins(play.PlayScala, SbtWeb).settings(
    name := "ui-piece",
    libraryDependencies ++= Seq(
      "com.typesafe.play"               %% "play-test"              % playVersion % "test"
    ),
    includeFilter in (Assets, LessKeys.less) := "*.less"
  ).dependsOn(common)

}
