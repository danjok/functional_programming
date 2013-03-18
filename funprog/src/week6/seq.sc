package week6

object seq {
  //vector is represented like a tree with node large up to 32 elements
  //for bulk operations Vector, for access to head,tail List
 	List(1,2,3)                               //> res0: List[Int] = List(1, 2, 3)
 	
 	val nums=Vector(1,2,3)                    //> nums  : scala.collection.immutable.Vector[Int] = Vector(1, 2, 3)
 	4+:nums                                   //> res1: scala.collection.immutable.Vector[Int] = Vector(4, 1, 2, 3)
 	nums:+4                                   //> res2: scala.collection.immutable.Vector[Int] = Vector(1, 2, 3, 4)
 	
 	val r : Range = 1 until 5                 //> r  : Range = Range(1, 2, 3, 4)
 	1 to 5                                    //> res3: scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5)
 	1 to 10 by 2                              //> res4: scala.collection.immutable.Range = Range(1, 3, 5, 7, 9)
 	6 to 1 by -2                              //> res5: scala.collection.immutable.Range = Range(6, 4, 2)
 	
 	
 	val s = "Hello world"                     //> s  : java.lang.String = Hello world
 	
 	s exists (c=>c.isUpper)                   //> res6: Boolean = true
 	s forall (c=>c.isUpper)                   //> res7: Boolean = false
 	
 	val pairs = List(1,2,3,4) zip s           //> pairs  : List[(Int, Char)] = List((1,H), (2,e), (3,l), (4,l))
 	pairs.unzip                               //> res8: (List[Int], List[Char]) = (List(1, 2, 3, 4),List(H, e, l, l))
 	
 	s flatMap(c=> List('.',c))                //> res9: String = .H.e.l.l.o. .w.o.r.l.d
 	nums.max                                  //> res10: Int = 3
 	nums.sum                                  //> res11: Int = 6
 	
 	
 	def scalarProduct(xs: Vector[Double], ys: Vector[Double]) : Double =
 		(xs zip ys).map(xy=> xy._1*xy._2).sum
                                                  //> scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double
  scalarProduct(Vector(2,3), Vector(4,5))         //> res12: Double = 23.0
 
  def scalarProduct2(xs: Vector[Double], ys: Vector[Double]) : Double =
		(xs zip ys).map{case(x,y)=>x*y}.sum
                                                  //> scalarProduct2: (xs: Vector[Double], ys: Vector[Double])Double
	val n=5                                   //> n  : Int = 5
	1 to n                                    //> res13: scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5)
	(1 to n).flatMap(x=>(1 to n).map(y=>(x,y) ))
                                                  //> res14: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,1), (1,
                                                  //| 2), (1,3), (1,4), (1,5), (2,1), (2,2), (2,3), (2,4), (2,5), (3,1), (3,2), (3
                                                  //| ,3), (3,4), (3,5), (4,1), (4,2), (4,3), (4,4), (4,5), (5,1), (5,2), (5,3), (
                                                  //| 5,4), (5,5))
	
	def isPrime(x:Int): Boolean = (2 until x) forall (d=> x%d !=0)
                                                  //> isPrime: (x: Int)Boolean
	isPrime(9)                                //> res15: Boolean = false
	
	
}