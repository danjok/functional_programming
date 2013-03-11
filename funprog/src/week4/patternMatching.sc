package week4
/*
	SOL1: CLASSIFICATION and ACCESSOR: 	quadratic explosion
	SOL2: TYPE TESTS and TYPE CAST:			low level and unsafe
	SOL3: OBJECT-ORIENTED DECOMPOSITION: need to touch all classes to add a new method
*/

/**
 * SOL4: FUNCTIONAL DECOMPOSITON WITH PATTERN MATCHING
 */
/*
Observations: the sole purpose of test and accessor functions is to reverse the construction process
	which subclass was used?
	what were the arguments of the constructor?
*/
/** CASE CLASS **/

trait Expr{
//eval using SOL4 with PATTERN MATCHING
  //is a generalization of switch from C to class hierarchies
  def eval: Int = this match {
    case Number(n) => n
    case Sum(Number(1), Number(2)) => 0
    case Sum(e1, e2) => e1.eval + e2.eval
  }
 
}
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
/*
Note: It also implicitly defines companion objects with apply methods
	object Number{
		def apply(n:Int) = new Number(n)
	}
*/

object patternMatching {
	Sum(Number(1), Number(2)).eval
  Number(1).eval
  /*
   def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
  }
  */
}

/*
	Considerations on extensebility of EXPRESSION PROBLEM (add new methods and/or new subclass)
	
	 													OO DECOMPOSITION											FUNCTIONAL DECOMPOSITION
	 													
	mostly new subclasses			local change: easy to create 					change code in base class for all
														a new subclasses with all methods			methods by adding a new case
														 					
	mostly new methods 				creation of a method in 							local change: add a new method
														all subclasses.PROBLEM								in base class with match and cases
*/