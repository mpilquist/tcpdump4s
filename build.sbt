scalaVersion := "3.1.3"

enablePlugins(ScalaNativePlugin)

resolvers ++= Resolver.sonatypeOssRepos("snapshots")

libraryDependencies ++= List(
  "com.armanbilge" %%% "ip4s-core" % "3.1.1-177-8abd977-SNAPSHOT",
  "com.armanbilge" %%% "fs2-protocols" % "3.2.10-17-20a277a-SNAPSHOT",
  "com.monovore" %%% "decline" % "2.3.1-SNAPSHOT"
)
