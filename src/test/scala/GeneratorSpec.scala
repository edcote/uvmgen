package uvmgen.tests

import org.scalatest._
import uvmgen._


class GeneratorSpec extends FlatSpec {
  "UVM Generator" should "do it's thing" in {
    def resourcePath(name: String): String = getClass.getResource(name).getPath

    val config = GeneratorConfig(
      uvcYamlPath = resourcePath("/uvc_example.yaml"),
      utbYamlPath = resourcePath("/utb_example.yaml")
    )

    val generator = new Generator()(config)
    generator.run()
  }

}

