package Impatient.Two

object One extends App {
  def signum(x: Int) = {
    if (x == 0) 0
    else if (x > 0) 1
    else -1
  }
val result = signum(-25487445)
  println(result)
}
