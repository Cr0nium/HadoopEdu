package Impatient.Two

object Seven extends App {
  def getUnicodeMulti(inputString: String){
    var prod: BigInt = 1
    val test: BigInt = 1
    for (c <- inputString) {
      println(test * c)
      prod *= c
    }
    println(prod)
  }
  def getUnicodeMultiInt(inputString: String){
    var prod: Int = 1
    val test: Int = 1
    for (c <- inputString) {
      println(test * c)
      prod *= c
    }
    println(prod)
  }
  getUnicodeMulti("Hello")
  getUnicodeMultiInt("Helloa")
}
