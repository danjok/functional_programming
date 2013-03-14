package week5

import math.Ordering

object merge_pairs {
  //Pair
  val pair = ("answer", 42)                       //> pair  : (java.lang.String, Int) = (answer,42)
  //pattern matching
  val (label, number) = pair                      //> label  : java.lang.String = answer
                                                  //| number  : Int = 42

  /**
   * *
   * 1) MSORT FOR INT
   * *
   */

  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
        //xs is empty
        case (Nil, ys) => ys
        //ys is empty
        case (xs, Nil) => xs
        //both xs and ys have at least one element
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
        //x is the first elem of list xs and xs1 is tail of xs
      }
      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
  }                                               //> msort: (xs: List[Int])List[Int]

  val nums = List(2, 4, 5, -1, 7)                 //> nums  : List[Int] = List(2, 4, 5, -1, 7)
  //split list at index n
  nums.splitAt(2)                                 //> res0: (List[Int], List[Int]) = (List(2, 4),List(5, -1, 7))
  msort(nums)                                     //> res1: List[Int] = List(-1, 2, 4, 5, 7)

  /**
   * *
   * 2) MSORT WITH PARAMETER
   * *
   */
  //parameterization of Sort
  //ls(less than) is th method for comparison
  def msortParam[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        //xs is empty
        case (Nil, ys) => ys
        //ys is empty
        case (xs, Nil) => xs
        //both xs and ys have at least one element
        case (x :: xs1, y :: ys1) =>
          if (lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
        //x is the first elem of list xs and xs1 is tail of xs
      }
      val (fst, snd) = xs splitAt n
      merge(msortParam(fst)(lt), msortParam(snd)(lt))
    }
  }                                               //> msortParam: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]

  val words = List("a", "v", "c", "d")            //> words  : List[java.lang.String] = List(a, v, c, d)
  msortParam(words)((x, y) => x.compareTo(y) < 0) //> res2: List[java.lang.String] = List(a, c, d, v)
  //type is inferred
  "a".compareTo("b")                              //> res3: Int = -1
	 
	 /**
   * *
   * 3) MSORT WITH PARAMETER USING ORDERING
   * *
   */
  //there is already a class in standard library that represents Ordering
  //if there is a single (most specific) definition, it will be taken as
  //actual argument for the implicit argument
  def msortParamOrder[T](xs: List[T])(ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        //xs is empty
        case (Nil, ys) => ys
        //ys is empty
        case (xs, Nil) => xs
        //both xs and ys have at least one element
        case (x :: xs1, y :: ys1) =>
          if (ord.lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
        //x is the first elem of list xs and xs1 is tail of xs
      }
      val (fst, snd) = xs splitAt n
      merge(msortParamOrder(fst)(ord), msortParamOrder(snd)(ord))
    }
  }                                               //> msortParamOrder: [T](xs: List[T])(ord: scala.math.Ordering[T])List[T]

  msortParamOrder(words)(Ordering.String)         //> res4: List[java.lang.String] = List(a, c, d, v)
  
  /**
   * *
   * 4) MSORT WITH IMPLICIT PARAMETER
   * *
   */
 	//Compile will figure out the right implicit type based on the demanding type
 	
 	def msortImplicit[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        //xs is empty
        case (Nil, ys) => ys
        //ys is empty
        case (xs, Nil) => xs
        //both xs and ys have at least one element
        case (x :: xs1, y :: ys1) =>
          if (ord.lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
        //x is the first elem of list xs and xs1 is tail of xs
      }
      val (fst, snd) = xs splitAt n
      merge(msortImplicit(fst), msortImplicit(snd))
    }
  }                                               //> msortImplicit: [T](xs: List[T])(implicit ord: scala.math.Ordering[T])List[T
                                                  //| ]

  msortImplicit(words)                            //> res5: List[java.lang.String] = List(a, c, d, v)
 	 
}