name := "playTest"

version := "0.1"

scalaVersion := "2.13.1"

//libraryDependencies ++=Seq("com.typesafe.play" %% "play" % "2.7.4")
libraryDependencies ++= {
  Seq(
//    "dev.morphia.morphia" % "core" % "1.5.8",
//    "online.licos" % "licos-json4scala_2.12" % "0.0.3",
    "org.reactivemongo" %% "reactivemongo" % "0.19.2",
    "com.typesafe.play" %% "play" % "2.7.4",
    "org.reactivemongo" %% "reactivemongo-play-json" % "0.19.3-play27",
    "org.reactivemongo" %% "play2-reactivemongo" % "0.19.3-play27"
  )
}