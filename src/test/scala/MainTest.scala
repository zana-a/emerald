import org.scalatest.FunSuite
import io.zana.emerald.Main

class MainTest extends FunSuite {

  test("greeting value in Main is \"Hello!\"") {
    assert(Main.greeting.equals("Hello!"))
  }
}
