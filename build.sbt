scalaVersion := "3.1.3"

enablePlugins(ScalaNativePlugin)

resolvers ++= Resolver.sonatypeOssRepos("snapshots")

libraryDependencies ++= List(
  // "com.armanbilge" %%% "ip4s-core" % "3.1.1-176-faff0ed-SNAPSHOT"
)
