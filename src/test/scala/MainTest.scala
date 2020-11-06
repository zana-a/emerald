import org.junit.Assert._
import org.junit._

class MainTest {

  def hello(name: String): String = s"Hello, $name"

  @Test
  def `says hello`(): Unit =  {
    val hello = this.hello("Zana")
    assertEquals(hello, "Hello, Zana")
  }
}
