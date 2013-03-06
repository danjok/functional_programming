package week1

object session {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  1 + 2                                           //> res0: Int(3) = 3
  1 +
    2                                             //> res1: Int(3) = 3
  def abs(x: Double) = if (x < 0) -x else x       //> abs: (x: Double)Double

  def sqrt(x: Double) = {
    //x is visible in nested functions
    //function are limited to function scope of sqrt
    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double) =
      abs(guess * guess - x) / x < 0.00001
    //in this way we make elements of comaparison proportianal

    def improve(guess: Double) =
      (guess + x / guess) / 2

    sqrtIter(1.0)
  }                                               //> sqrt: (x: Double)Double

  sqrt(2)                                         //> res2: Double = 1.4142156862745097
  sqrt(4)                                         //> res3: Double = 2.0000000929222947
  sqrt(1e-6)                                      //> res4: Double = 0.0010000001533016628
  sqrt(1e60)                                      //> res5: Double = 1.0000000031080746E30

}