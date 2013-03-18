package week5

object reductionOfList {

  val nums = List(1, 2, 4, 42, -1, 0)             //> nums  : List[Int] = List(1, 2, 4, 42, -1, 0)
  val empty = List()                              //> empty  : List[Nothing] = List()
  /**
   * *
   * REDUCE
   * *
   */
  def sum1(xs: List[Int]): Int = xs match {
    case Nil => 0
    case y :: ys => y + sum1(ys)
  }                                               //> sum1: (xs: List[Int])Int
  //the _ represents a new paarameter
  def sum(xs: List[Int]): Int = 0 :: xs reduceLeft (_ + _)
                                                  //> sum: (xs: List[Int])Int
  def product(xs: List[Int]): Int = 1 :: xs reduceLeft (_ * _)
                                                  //> product: (xs: List[Int])Int
  sum(nums)                                       //> res0: Int = 48

  /**
   * *
   * FOLD
   * *
   */

  //A more general definition of reduce is fold
  //it can be called on an Empty list
  //it takes an accumulator as additional paramter

  def sum2(xs: List[Int]): Int = (xs foldLeft 0)(_ + _)
                                                  //> sum2: (xs: List[Int])Int
  def sum3(xs: List[Int]): Int = (xs foldRight 0)(_ + _)
                                                  //> sum3: (xs: List[Int])Int
  sum2(nums)                                      //> res1: Int = 48
  sum2(empty)                                     //> res2: Int = 0
  sum3(nums)                                      //> res3: Int = 48
  sum3(empty)                                     //> res4: Int = 0

  def concat[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldRight ys)(_ :: _)                     //> concat: [T](xs: List[T], ys: List[T])List[T]
  /*** (xs foldLeft ys ) (_::_)  doesn't work ! ***/
	//Why? see expression tree
}

/*
	abstract class List[T] {
	
	//this is the reduce definition in List class
 		def reduceLeft(op: (T,T)=>T) : T = this match {
 			case Nil => throw new Error("Nil.reduceLeft")
 			case x::xs => (xs foldLeft x)(op)
 		}
	
	//this is the fold definition in List class
 		def foldLeft[U](z:U)(op: (U,T)=>U) : U = this match {
 			case Nil => z
 			case x::xs => (xs foldLeft op(z,x))(op)
 		}
	
	//Note: z is the neutral element, the operation is  (U,T) => U, and  T can be also Empty
	
	//for the right
		def reduceRight(op: (T,T)=>T) : T = this match {
 			case Nil => throw new Error("Nil.reduceRight")
 			case x::xs => op(x, xs.reduceRight(op))
 		}
 		
 		def foldLeft[U](z:U)(op: (U,T)=>U) : U = this match {
 			case Nil => z
 			case x::xs => op(x, xs.foldRight z (op ))
 		}
 		
 	}
 		Reduce Left
			 		 op
 			 	.			\
			 op		 		xn
		 / 		\
		x1 		x2
		
		
		Fold Left
			 		 op:U
 			 	.				\
			 op:U			 xn:T
		 / 		\
		z:U 	x1:T
	
*/

/*
	http://oldfashionedsoftware.com/2009/07/10/scala-code-review-foldleft-and-foldright/
	The foldRight function isn't implemented with tail recursion, so it can't work with long list
	*/