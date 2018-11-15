package uvmgen

import scala.io.Source
import scala.util.{Failure, Success, Try}


case class GeneratorConfig(private val uvcYamlPath: String = "", private val utbYamlPath: String = "", outputDir: String = "/tmp", verbose: Boolean = false) {
  lazy val uvcYaml: String = Try(Source.fromFile(uvcYamlPath).mkString) match {
    case Success(v) => v
    case Failure(e) => throw GeneratorError(e.getMessage, e)
  }

  lazy val utbYaml: String = Try(Source.fromFile(utbYamlPath).mkString) match {
    case Success(v) => v
    case Failure(e) => throw GeneratorError(e.getMessage, e)
  }
}

