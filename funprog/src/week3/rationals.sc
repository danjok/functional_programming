package week3

object rationals {
  val x = new Rational(1, 3)                      //> x  : week3.Rational = 1/3
  val y = new Rational(5, 7)                      //> y  : week3.Rational = 5/7
  val z = new Rational(3, 2)                      //> z  : week3.Rational = 3/2
  //val strange = new Rational(1, 0)
  //strange.add(strange)
  x.num                                           //> res0: Int = 1
  x.den                                           //> res1: Int = 3
  x.sub(y).sub(z)                                 //> res2: week3.Rational = -79/42
  y.add(y)                                        //> res3: week3.Rational = 10/7
  y.less(x)                                       //> res4: Boolean = false
 	new Rational(3)                           //> res5: week3.Rational = 3/1
 	// infix notation
 	y less x                                  //> res6: Boolean = false
 	y add y                                   //> res7: week3.Rational = 10/7
 	y + y                                     //> res8: week3.Rational = 10/7
}

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nozero")
  //if it fails, it throws IllegalArgumentException
  assert(y!=0)
  //if it fails, it throws java.lang.AssertionError
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  //members
  private def g = gcd(x, y)
  def num = x / g
  def den = y / g
  //Use val if num and den are called often, so computation is done only once

	//other constructor
	def this(x:Int) = this(x,1)
  //methods
  def add(that: Rational) =
    new Rational(
      num * that.den + that.num * den,
      den * that.den)
  def neg: Rational = new Rational(-num, den)

  def sub(that: Rational) = add(that.neg)

  def less(that: Rational) = this.num * that.den < that.num * this.den
  
  def max(that: Rational) = if (this.less(that)) that else this

  override def toString = num + "/" + den

	//relaxed operators
  def < (that: Rational) = this.num * that.den < that.num * this.den
	def + (that: Rational) =
    new Rational(
      num * that.den + that.num * den,
      den * that.den)
  def - (that: Rational) = this + -that
  def unary_- : Rational = new Rational(-num, den)
  //unary because the operator - is already defined, but this is different
  
}