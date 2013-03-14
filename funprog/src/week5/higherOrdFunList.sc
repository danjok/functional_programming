package week5

object higherOrdFunList {
  /**
   * *
   * MAP
   * *
   */

  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case y :: ys => y * factor :: scaleList(ys, factor)
  }                                               //> scaleList: (xs: List[Double], factor: Double)List[Double]
  // using map
  def scaleList2(xs: List[Double], factor: Double) = xs map (x => x * factor)
                                                  //> scaleList2: (xs: List[Double], factor: Double)List[Double]
  scaleList(List(2, 34, 5), 2)                    //> res0: List[Double] = List(4.0, 68.0, 10.0)
  scaleList2(List(2, 34, 5), 2)                   //> res1: List[Double] = List(4.0, 68.0, 10.0)

  /**
   * *
   * FILTER
   * *
   */
  def posElem(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case y :: ys => if (y > 0) y :: posElem(ys) else posElem(ys)
  }                                               //> posElem: (xs: List[Int])List[Int]
  //using filter
  def posElem2(xs: List[Int]) = xs filter (x => x > 0)
                                                  //> posElem2: (xs: List[Int])List[Int]
  /**
   * *
   * VARIATIONS OF FILTER
   * *
   */
	val nums = List(1, 2, -3, 3,-4, 0,2,3)    //> nums  : List[Int] = List(1, 2, -3, 3, -4, 0, 2, 3)
	
	nums filter (x => x>0)                    //> res2: List[Int] = List(1, 2, 3, 2, 3)
	nums filterNot (x => x>0)                 //> res3: List[Int] = List(-3, -4, 0)
	//filter and filterNot in a single traversal
	nums partition (x=> x>0)                  //> res4: (List[Int], List[Int]) = (List(1, 2, 3, 2, 3),List(-3, -4, 0))
	
	//return the prefix of list until matching condition
	nums takeWhile (x => x >0 )               //> res5: List[Int] = List(1, 2)
	//return the remainder of list without the prefix taken from takeWhile
	nums dropWhile (x => x > 0 )              //> res6: List[Int] = List(-3, 3, -4, 0, 2, 3)
	//takeWhile and dropWhile in a single traversal
	nums span (x => x>0)                      //> res7: (List[Int], List[Int]) = (List(1, 2),List(-3, 3, -4, 0, 2, 3))
	
	
	//Exercise:
	//Write a function that packs consecutive duplicate of list element into sublists
	val data = List("a","a","a","b","b","c","a")
                                                  //> data  : List[java.lang.String] = List(a, a, a, b, b, c, a)
	def pack[T](xs: List[T]) : List[List[T]] = xs match {
		case Nil => Nil
		case x::xs1 =>
			val (first, other) = xs span ( y => y==x )
			first:: pack(other)
	}                                         //> pack: [T](xs: List[T])List[List[T]]
	
	pack(data)                                //> res8: List[List[java.lang.String]] = List(List(a, a, a), List(b, b), List(c
                                                  //| ), List(a))
	//encode is a function that produces the runlength encoding of a list
	def encode[T](xs: List[T]): List[(T,Int)] = pack(xs) map (ys => (ys.head, ys.length))
                                                  //> encode: [T](xs: List[T])List[(T, Int)]
	encode(data)                              //> res9: List[(java.lang.String, Int)] = List((a,3), (b,2), (c,1), (a,1))
}


//this is the map definition of List class
  /*
	abstract class List[T] {
 		def map[U](f: T=>U) : List[U] = this match {
 			case Nil => this
 			case x::xs => f(x) :: map(f)
 		}
 		
 		def filter(p:T=>Boolean) : List[T] = this match {
 			case Nil => this
 			case x::xs => if (p(x)) x::xs.filter(p) else xs.filter(p)
 		
 		}
 	}
	*/