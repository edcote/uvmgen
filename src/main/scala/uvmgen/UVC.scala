package uvmgen

import java.io.{BufferedWriter, File, FileWriter}

import scala.io.Source


case class UVC(name: String)(implicit val config: GeneratorConfig) {
  def resourcePath(name: String): String = getClass.getResource(name).getPath

  private val all = Seq(resourcePath("/uvcgen_agent.svh"),
    resourcePath("/uvcgen_config.svh"),
    resourcePath("/uvcgen_driver.svh"),
    resourcePath("/uvcgen_if.sv"),
    resourcePath("/uvcgen_monitor.svh"),
    resourcePath("/uvcgen_pkg.sv")
  )

  def emit(): Unit = {
    for (abspath <- all) {
      val inputFile = new File(abspath)
      val reader = Source.fromFile(inputFile)

      val outputDir = new File(config.outputDir)
      val outputPath = config.outputDir + "/" + inputFile.getName.replace("utbgen", name)
      val writer = new BufferedWriter(new FileWriter(outputPath))

      if (!outputDir.exists())
        if (!new File(config.outputDir).mkdirs())
          throw GeneratorError("Unable to create output directory: %s".format(config.outputDir))

      for (line <- reader.getLines) {
        val lineToEmit = line.replaceAll("utbgen", name)
        writer.write(lineToEmit + "\n")
      }

      writer.close()
    }
  }


}


