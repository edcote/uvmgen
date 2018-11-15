package uvmgen

import java.io.{BufferedWriter, File, FileWriter}

import scala.io.Source


case class UTB(name: String, uvcs: Seq[UVC] = Seq())(implicit val config: GeneratorConfig) {
  def resourcePath(name: String): String = getClass.getResource(name).getPath

  private val all = Seq(resourcePath("/utbgen_env.svh"),
    resourcePath("/utbgen_monitor.svh"),
    resourcePath("/utbgen_tb.sv"),
    resourcePath("/utbgen_virtual_sequencer.svh")
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