name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val myProject = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.typesafe.play" %% "play-json" % "2.3.4",
  "org.apache.directory.studio" % "org.apache.commons.io" % "2.4",
  "com.amazonaws" % "aws-java-sdk" % "1.11.111",
  "com.typesafe.play" %% "play-json" % "2.3.4"
)


//excludeFilter in (Compile, unmanagedSources) := "MessageController.java" || "MessageCreationDto.java" || "MessageDto.java" || "MessageAdmin.java" || "ResponseMessageForm.java" || "Message.java"





fork in run := true