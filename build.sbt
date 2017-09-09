import java.nio.file.{Files, Paths, StandardCopyOption}

import sbt._
import org.apache.commons.io
import org.apache.commons.io.FileUtils

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)

name := "solitairScala"

version := "1.0"

scalaVersion := "2.12.3"

scalaJSUseMainModuleInitializer := true

useYarn := true
resolvers += Resolver.jcenterRepo

libraryDependencies += "com.definitelyscala" %%% "scala-js-phaser" % "1.0.2"
libraryDependencies += "io.monix" %%% "monix" % "2.3.0"

webpackConfigFile in fullOptJS := Some(baseDirectory.value / "webpack.release.config.js")
webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack.dev.config.js")

npmDevDependencies in Compile += "webpack-merge" -> "4.1.0"

npmDevDependencies in Compile += "compression-webpack-plugin" -> "*"
npmDevDependencies in Compile += "webpack-closure-compiler" -> "*"
npmDevDependencies in Compile += "expose-loader" -> "0.7.1"
npmDevDependencies in Compile += "script-loader" -> "*"


npmDependencies in Compile += "phaser" -> "2.6.2"
npmDependencies in Compile += "imports-loader" -> "*"

lazy val release = taskKey[Unit]("Build a release version of the game")
lazy val devBuild = taskKey[Unit]("Builds a dev version of the game")

lazy val testes = taskKey[Unit]("An example task")
lazy val cleanTarget = taskKey[Unit]("Clean Target Directory")

emitSourceMaps in fastOptJS := false
enableReloadWorkflow in fastOptJS := true

cleanTarget := {

  var targetDir =  baseDirectory.value / "target" / "scala-2.12" /"scalajs-bundler" / "main"

  val files = targetDir.listFiles()
  files.filter ((f)=> f.name.endsWith(".js")
    || f.name.endsWith(".gz")
    || f.name.endsWith(".map")).map(_.delete)

}

devBuild := {

  val t = webpack.in(fastOptJS).in(Compile).value
  val toCopy = baseDirectory.value / "target" / "scala-2.12" /"scalajs-bundler" / "dev"
  val html = baseDirectory.value / "src" / "main" / "web"

  if(toCopy.exists)
    FileUtils.cleanDirectory(toCopy)

  FileUtils.copyDirectory(html,toCopy)
  FileUtils.forceDelete(toCopy / "index.html")
  FileUtils.moveFile( toCopy / "indexDev.html",toCopy / "index.html")

  t.map ((file)=>{
    Files.copy(file.toPath,(toCopy / file.name).toPath,StandardCopyOption.REPLACE_EXISTING)
    val map = new File(file.toPath + ".map")
    if(map.exists)
      FileUtils.copyFile(map,toCopy)
  })
}

release := {



   val t = webpack.in(fullOptJS).in(Compile).value
   val toCopy = baseDirectory.value / "target" / "scala-2.12" /"scalajs-bundler" / "release"
   val html = baseDirectory.value / "src" / "main" / "web"

  if(toCopy.exists)
    FileUtils.cleanDirectory(toCopy)

  FileUtils.copyDirectory(html,toCopy)
  FileUtils.forceDelete(toCopy / "indexDev.html")

    t.map ((file)=>{


      Files.copy(file.toPath,(toCopy / file.name).toPath,StandardCopyOption.REPLACE_EXISTING)

      val gz = new File(file.toPath + ".gz")
      val map = new File(file.toPath + ".map")

      if(gz.exists)
        FileUtils.copyFile(gz,toCopy)

      if(map.exists)
        FileUtils.copyFile(map,toCopy)

    })

}