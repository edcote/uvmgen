package uvmgen

case class UTB(name: String, uvcs: Seq[UVC] = Seq()) {
  def resourcePath(name: String): String = getClass.getResource(name).getPath

  private val all = Seq(resourcePath("/utbgen_env.svh"),
    resourcePath("/utbgen_monitor.svh"),
    resourcePath("/utbgen_tb.sv"),
    resourcePath("/utbgen_virtual_sequencer.svh")
  )

  def emit: Unit = {
    for (file <- all) {
      println(file)
    }
    ""
  }
}