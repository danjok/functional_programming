package week2
import math.abs

object fixedPoint {
  val tolerance = 0.0001 //> tolerance  : Double = 1.0E-4
  def isCloseEnough(a: Double, b: Double) =
    abs((a - b) / a) / a < tolerance //> isCloseEnough: (a: Double, b: Double)Boolean

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  } //> fixedPoint: (f: Double => Double)(firstGuess: Double)Double

  fixedPoint(x => 1 + x / 2)(1) //> res0: Double = 1.999755859375
  //problem: we have oscillations
  def sqrt(x: Double) = fixedPoint(y => x / y)(1) //> sqrt: (x: Double)Double
  //sqrt(2)
  //you have to prevent the extimation from varying too much
  //you can averaging successive values of the original sequence
  def sqrt2(x: Double) = fixedPoint(y => (y + x / y) / 2)(1)
  //> sqrt2: (x: Double)Double
  sqrt2(2) //> res1: Double = 1.4142135623746899
  //AVERAGE DAMPING
  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2
  //> averageDamp: (f: Double => Double)(x: Double)Double
  //function that takes a function and returns a function
  def sqrt3(x: Double) =
    fixedPoint(averageDamp(y => x / y))(1) //> sqrt3: (x: Double)Double
  sqrt3(2) //> res2: Double = 1.4142135623746899

}