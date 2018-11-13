package uvmgen.tests

import org.scalatest._
import uvmgen._

class GeneratorSpec extends FlatSpec {

  "A UVM Generator" should "do it's thing" in {
    def resourcePath(name: String): String = getClass.getResource(name).getPath

    val config = GeneratorConfig(
      uvcfile = resourcePath("/uvc_example.yaml"),
      utbfile = resourcePath("/utb_example.yaml")
    )

    val generator = new Generator(config)
    generator.run()
  }

}

