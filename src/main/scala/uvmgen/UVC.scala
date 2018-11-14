package uvmgen

case class UVC(name: String) {
  def resourcePath(name: String): String = getClass.getResource(name).getPath

  private val all = Seq(resourcePath("/uvcgen_agent.svh"),
    resourcePath("/uvcgen_config.svh"),
    resourcePath("/uvcgen_driver.svh"),
    resourcePath("/uvcgen_if.sv"),
    resourcePath("/uvcgen_monitor.svh"),
    resourcePath("/uvcgen_pkg.sv")
  )

  def emit: Unit = {
    ""
  }
}


