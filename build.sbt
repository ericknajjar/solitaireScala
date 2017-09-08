import java.nio.file.{Files, StandardCopyOption, Paths}

import sbt._

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)

name := "solitairScala"

version := "1.0"

scalaVersion := "2.12.1"

scalaJSUseMainModuleInitializer := true

useYarn := true
resolvers += Resolver.jcenterRepo

libraryDependencies += "com.definitelyscala" %%% "scala-js-phaser" % "1.0.2"

webpackConfigFile := Some(baseDirectory.value / "webpack.config.js")

npmDevDependencies in Compile += "webpack-merge" -> "4.1.0"

npmDevDependencies in Compile += "compression-webpack-plugin" -> "*"
npmDevDependencies in Compile += "webpack-closure-compiler" -> "*"
npmDevDependencies in Compile += "expose-loader" -> "0.7.1"
npmDevDependencies in Compile += "script-loader" -> "*"

npmDependencies in Compile += "phaser" -> "2.6.2"
npmDependencies in Compile += "imports-loader" -> "*"

lazy val release = taskKey[Unit]("Build a release version of the game")
lazy val testes = taskKey[Unit]("An example task")
lazy val cleanTarget = taskKey[Unit]("Clean Target Directory")

cleanTarget := {

  var targetDir =  baseDirectory.value / "target" / "scala-2.12" /"scalajs-bundler" / "main"

  val files = targetDir.listFiles()
  files.filter (_.name.endsWith(".js")).map(_.delete)
}

release := {



   val t = webpack.in(fullOptJS).in(Compile).value
   val toCopy = baseDirectory.value / "target" / "scala-2.12" /"scalajs-bundler" / "release"
   val html = baseDirectory.value / "src" / "main" / "web" / "index.html"

  if (!toCopy.exists())
    toCopy.mkdirs()

   Files.copy(html.toPath,(toCopy /html.name).toPath,  StandardCopyOption.REPLACE_EXISTING)

    t.map ((file)=>{


      Files.copy(file.toPath,(toCopy / file.name).toPath,StandardCopyOption.REPLACE_EXISTING)

      val gz = new File(file.toPath + ".gz")
      val map = new File(file.toPath + ".map")

      Files.copy(gz.toPath,(toCopy / (gz.name)).toPath,StandardCopyOption.REPLACE_EXISTING)
      Files.copy(map.toPath,(toCopy / (map.name)).toPath,StandardCopyOption.REPLACE_EXISTING)
    })

}