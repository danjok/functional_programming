package week5

object list {

  // operator :: associates to right
  val letters = "a" :: "b" :: Nil                 //> letters  : List[java.lang.String] = List(a, b)
  val letters2 = Nil.::("b").::("a")              //> letters2  : List[java.lang.String] = List(a, b)
  val l = List(7, 3, 9, 2)                        //> l  : List[Int] = List(7, 3, 9, 2)

  l.head                                          //> res0: Int = 7
  l.tail                                          //> res1: List[Int] = List(3, 9, 2)
  l.last                                          //> res2: Int = 2
  l.init                                          //> res3: List[Int] = List(7, 3, 9)
  l.isEmpty                                       //> res4: Boolean = false
  l.length                                        //> res5: Int = 4

  l take 2                                        //> res6: List[Int] = List(7, 3)
  l take 10                                       //> res7: List[Int] = List(7, 3, 9, 2)

  l drop 2                                        //> res8: List[Int] = List(9, 2)
  l(1)                                            //> res9: Int = 3
  //or
  l apply 1                                       //> res10: Int = 3

  val l2 = List(4, 5, 1, 0)                       //> l2  : List[Int] = List(4, 5, 1, 0)
  l ++ l2                                         //> res11: List[Int] = List(7, 3, 9, 2, 4, 5, 1, 0)
  l.reverse                                       //> res12: List[Int] = List(2, 9, 3, 7)
  l updated (2, 4)                                //> res13: List[Int] = List(7, 3, 4, 2)

  l indexOf 5                                     //> res14: Int = -1
  l indexOf 7                                     //> res15: Int = 0
  l contains 7                                    //> res16: Boolean = true

  def evalList[T](l: List[T]): String = l match {
    case Nil => "empty"
    case x :: Nil => "list of 1 element"
    case "a" :: xs => "list starting with a"
    //case "a"::"b"::Nil => "matched"
    case x :: y :: List(xs, ys) :: zs => "list >= 3"
    case _ => "default"
  }                                               //> evalList: [T](l: List[T])String

  evalList(letters)                               //> res17: String = list starting with a

  //Insertion sort
  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => insert(y, isort(ys))
  }                                               //> isort: (xs: List[Int])List[Int]

  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
  }                                               //> insert: (x: Int, xs: List[Int])List[Int]
  //complexity N*N
  isort(l)                                        //> res18: List[Int] = List(2, 3, 7, 9)

  def last[T](xs: List[T]): T = xs match {
    case List() => throw new Error("empty list")
    case List(x) => x
    case y :: ys => last(ys)
  }                                               //> last: [T](xs: List[T])T
  last(List(1, 2, 3, 4))                          //> res19: Int = 4
	//complexity N
	
  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }                                               //> init: [T](xs: List[T])List[T]
  init(List(1, 2, 3, 4))                          //> res20: List[Int] = List(1, 2, 3)
	//complexity N-1

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List() => ys
    case z :: zs => z :: concat(zs, ys)
  }                                               //> concat: [T](xs: List[T], ys: List[T])List[T]
  //complexity |xs|

	def reverse[T](xs: List[T]): List[T] = xs match {
    case List() => xs
    case y :: ys => reverse(ys) ++ List(y)
  }                                               //> reverse: [T](xs: List[T])List[T]
  //complexity N*N (for concat and for reverse)
  
  def removeAt[T](xs: List[T], n: Int): List[T] = (xs take n) ++ (xs drop n+1)
                                                  //> removeAt: [T](xs: List[T], n: Int)List[T]
  //(xs take n) ::: (xs drop n+1)
}