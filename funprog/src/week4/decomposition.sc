package week4
/**
 * *
 * DECOMPOSTION
 * *
 */
//Find a convenient way to access objects in a extensible class hierarchy

object decomposition {
  //eval using SOL1
  def eval1(e: Expr1): Int = {
    if (e.isNumber) e.numValue
    else if (e.isSum) eval1(e.leftOp) + eval1(e.rightOp)
    else throw new Error("Unkown expr " + e)
  }                                               //> eval1: (e: week4.Expr1)Int

}

/**
 * SOL1: CLASSIFICATION and ACCESSOR
 */
/*
	the problem with this solution is that it has a QUADRATIC increase of method
	with adding a new operation!
	NO!
*/
trait Expr1 {
  //classification
  def isNumber: Boolean
  def isSum: Boolean
  //accessor
  def numValue: Int
  def leftOp: Expr1
  def rightOp: Expr1
}

class Number1(n: Int) extends Expr1 {
  def isNumber: Boolean = true
  def isSum: Boolean = false
  def numValue: Int = n
  def leftOp: Expr1 = throw new Error("Number1.leftOp")
  def rightOp: Expr1 = throw new Error("Number1.rightOp")
}

class Sum1(e1: Expr1, e2: Expr1) extends Expr1 {
  def isNumber: Boolean = false
  def isSum: Boolean = true
  def numValue: Int = throw new Error("Sum1.numValue")
  def leftOp: Expr1 = e1
  def rightOp: Expr1 = e2
}

/**
 * SOL2: TYPE TESTS and TYPE CAST
 */
/*
	def isInstanceOf[T]: Boolean
	def asInstanceOf[T]: T
	
	no need for classification methods
	low-level and potentially unsafe
	NO!
*/

/**
 * SOL3: OBJECT-ORIENTED DECOMPOSITION
 */
/*
	it is only good for evaluation method
	If you want to display expressions you need to add new methods in all subclasses!
	If you want to simplify expressions like here:
			a*b+a*c => a*(b+c)
	you can't encapsulated in a method of a single object
	-> again quadratic complexity: you need test and access methods for all subclasses
	Problem with NON LOCAL SIMPLIFICATION
*/

trait Expr3 {
  def eval: Int
}

class Number3(n: Int) extends Expr3 {
  def eval: Int = n
}

class Sum3(e1: Expr3, e2: Expr3) extends Expr3 {
  def eval: Int = e1.eval + e2.eval
}