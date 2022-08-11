scalaVersion := "3.1.3"

enablePlugins(ScalaNativePlugin)

resolvers ++= Resolver.sonatypeOssRepos("snapshots")

libraryDependencies ++= List(
  "com.armanbilge" %%% "ip4s-core" % "3.1.1-177-8abd977-SNAPSHOT",
  "com.armanbilge" %%% "fs2-protocols" % "3.2.12-4-9e9986f-SNAPSHOT",
  "com.armanbilge" %%% "decline" % "2.2.1-SNAPSHOT"
)
