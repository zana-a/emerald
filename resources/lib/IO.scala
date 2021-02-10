object IO {

  import scala.io.StdIn.readLine

  def puts(string: Any): Unit = println(string)

  def gets(prompt: String): String = readLine(prompt)
}
