package uvmgen

case class UVC(name: String) {
  def resourcePath(name: String): String = getClass.getResource(name).getPath

  private val agent = resourcePath("utbgen_agent.svh")
  private val config = resourcePath("utbgen_config.svh")
  private val driver = resourcePath("utbgen_driver.svh")
  private val interface = resourcePath("utbgen_if.sv")
  private val monitor = resourcePath("utbgen_monitor.svh")
  private val pkg = resourcePath("utbgen_pkg.sv")

  def emit: String = {
    ""
  }
}


