scalaVersion := "3.2.0"

enablePlugins(ScalaNativePlugin)

resolvers ++= Resolver.sonatypeOssRepos("snapshots")

libraryDependencies ++= List(
  "com.comcast" %%% "ip4s-core" % "3.2.0",
  "co.fs2" %%% "fs2-protocols" % "3.3.0",
  "com.armanbilge" %%% "decline" % "2.2.1-SNAPSHOT"
)
