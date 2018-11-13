package uvmgen

case class UTB(name: String, uvcs: Seq[UVC] = Seq()) {
  def resourcePath(name: String): String = getClass.getResource(name).getPath

  private val env = resourcePath("utbgen_env.svh")
  private val monitor = resourcePath("utbgen_monitor.svh")
  private val tb = resourcePath("utbgen_tb.sv")
  private val virtual_sequencer = resourcePath("utbgen_virtual_sequencer.svh")

  def emit: String = {
    println(name)
    ""
  }
}