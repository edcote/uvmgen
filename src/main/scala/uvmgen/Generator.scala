package uvmgen

import cats.syntax.either._
import io.circe.generic.auto._
import io.circe.yaml.parser

import scala.io.Source
import scala.util.{Failure, Success, Try}


case class GeneratorError(private val message: String = "Something went terribly wrong",
                          private val cause: Throwable = None.orNull) extends Exception(message, cause)


case class GeneratorConfig(private val uvcfile: String = "", private val utbfile: String = "", verbose: Boolean = false) {
  lazy val uvcyaml: String = Try(Source.fromFile(uvcfile).mkString) match {
    case Success(v) => v
    case Failure(e) => throw GeneratorError(e.getMessage, e)
  }

  lazy val utbyaml: String = Try(Source.fromFile(utbfile).mkString) match {
    case Success(v) => v
    case Failure(e) => throw GeneratorError(e.getMessage, e)
  }
}

class Generator(private val config: GeneratorConfig) {
  private lazy val uvc: UVC = parser
    .parse(config.uvcyaml)
    .leftMap(e => throw GeneratorError(e.getMessage))
    .flatMap(_.as[UVC])
    .valueOr(e => throw GeneratorError("Unable to parse UVC configuration", e))

  private lazy val utb: UTB = parser
    .parse(config.utbyaml)
    .leftMap(e => throw GeneratorError(e.getMessage))
    .flatMap(_.as[UTB])
    .valueOr(e => throw GeneratorError("Unable to parse UTB configuration", e))

  def run(): Unit = {
  }
}

object Generator extends App {
  val parser = new scopt.OptionParser[GeneratorConfig]("uvmgen") {
    head("uvmgen", "1.0")
    opt[String]("uvcfile")
      .action((x, c) => c.copy(uvcfile = x))
      .required()
      .text("path to uvc .yaml configuration file")

    opt[String]("utbfile")
      .action((x, c) => c.copy(utbfile = x))
      .required()
      .text("path to utb .yaml configuration file")
  }

  parser.parse(args, GeneratorConfig()) match {
    case Some(config) => new Generator(config)
    case None =>
      sys.error("Error: command line parsing failed")
      sys.exit(1)
  }

}
