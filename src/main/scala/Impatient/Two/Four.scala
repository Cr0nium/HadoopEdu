package Impatient.Two

object Four extends App {
  def countdown(n: Int){
    for (i <- n to (0,-1) ) println(i)
  }
  countdown(10)
}
