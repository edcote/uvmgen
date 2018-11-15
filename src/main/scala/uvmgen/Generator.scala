package uvmgen

import cats.syntax.either._
import io.circe.generic.auto._
import io.circe.yaml.parser


case class GeneratorError(private val message: String = "Something went terribly wrong",
                          private val cause: Throwable = None.orNull) extends Exception(message, cause)


class Generator()(implicit private val config: GeneratorConfig) {
  private lazy val uvc: UVC = parser
    .parse(config.uvcYaml)
    .leftMap(e => throw GeneratorError(e.getMessage))
    .flatMap(_.as[UVC])
    .valueOr(e => throw GeneratorError("Unable to parse UVC configuration", e))

  private lazy val utb: UTB = parser
    .parse(config.utbYaml)
    .leftMap(e => throw GeneratorError(e.getMessage))
    .flatMap(_.as[UTB])
    .valueOr(e => throw GeneratorError("Unable to parse UTB configuration", e))

  def run(): Unit = {
    utb.emit
  }
}

object Generator extends App {
  val parser = new scopt.OptionParser[GeneratorConfig]("uvmgen") {
    head("uvmgen", "1.0")
    opt[String]("uvcfile")
      .action((x, c) => c.copy(uvcYamlPath = x))
      .required()
      .text("path to uvc .yaml configuration file")

    opt[String]("utbfile")
      .action((x, c) => c.copy(utbYamlPath = x))
      .required()
      .text("path to utb .yaml configuration file")

    opt[String]("outputdir")
      .action((x, c) => c.copy(outputDir = x))
      .text("output directory")

    opt[Boolean]("verbose")
      .action((x, c) => c.copy(verbose = x))
      .text("verbose output")

  }

  parser.parse(args, GeneratorConfig()) match {
    case Some(config) =>
      new Generator()(config)
    case None =>
      sys.error("Error: command line parsing failed")
      sys.exit(1)
  }

}
