ThisBuild / scalaVersion := "2.13.0"
ThisBuild / organization := "network.fst.example"

val JacksonVersion = "2.9.9"
val LogbackVersion = "1.2.3"

lazy val example = (project in file("."))
  .settings(
    name := "jvm-example",
    libraryDependencies ++= Seq(
      "org.apache.httpcomponents"    %  "httpclient"           % "4.5.9",
      "com.fasterxml.jackson.core"   %  "jackson-databind"     % JacksonVersion,
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % JacksonVersion,
      "org.slf4j"                    %  "slf4j-api"            % "1.7.28",
      "ch.qos.logback"               %  "logback-core"         % LogbackVersion,
      "ch.qos.logback"               %  "logback-classic"      % LogbackVersion,
      "org.web3j"                    %  "core"                 % "4.4.1"
    )
  )
