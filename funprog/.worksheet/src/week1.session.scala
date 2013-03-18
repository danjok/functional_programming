package week1

object session {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 
  println("Welcome to the Scala worksheet");$skip(8); val res$0 = 
  1 + 2;System.out.println("""res0: Int(3) = """ + $show(res$0));$skip(12); val res$1 = 
  1 +
    2;System.out.println("""res1: Int(3) = """ + $show(res$1));$skip(44); 
  def abs(x: Double) = if (x < 0) -x else x;System.out.println("""abs: (x: Double)Double""");$skip(466); 

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
  };System.out.println("""sqrt: (x: Double)Double""");$skip(11); val res$2 = 

  sqrt(2);System.out.println("""res2: Double = """ + $show(res$2));$skip(10); val res$3 = 
  sqrt(4);System.out.println("""res3: Double = """ + $show(res$3));$skip(13); val res$4 = 
  sqrt(1e-6);System.out.println("""res4: Double = """ + $show(res$4));$skip(13); val res$5 = 
  sqrt(1e60);System.out.println("""res5: Double = """ + $show(res$5))}

}