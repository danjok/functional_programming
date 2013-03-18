package week6

object pairs {
  val n = 7                                       //> n  : Int = 7
  def isPrime(x: Int): Boolean = (2 until x) forall (d => x % d != 0)
                                                  //> isPrime: (x: Int)Boolean
  //it is a Vector of Vector
  val xss = (1 until n) map (i => (1 until i) map (j => (i, j)))
                                                  //> xss  : scala.collection.immutable.IndexedSeq[scala.collection.immutable.Inde
                                                  //| xedSeq[(Int, Int)]] = Vector(Vector(), Vector((2,1)), Vector((3,1), (3,2)), 
                                                  //| Vector((4,1), (4,2), (4,3)), Vector((5,1), (5,2), (5,3), (5,4)), Vector((6,1
                                                  //| ), (6,2), (6,3), (6,4), (6,5)))

  xss.flatten                                     //> res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,1
                                                  //| ), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), (6,
                                                  //| 3), (6,4), (6,5))
  //this is a useful law
  //xs flatMap f = (xs map f ).flatten
  (1 until n) flatMap (i => (1 until i) map (j => (i, j)))
                                                  //> res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,1
                                                  //| ), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), (6,
                                                  //| 3), (6,4), (6,5))
  /** for expression **/
  case class Person(name: String, age: Int)

  val persons = Seq(Person("dani", 23), Person("ale", 18))
                                                  //> persons  : Seq[week6.pairs.Person] = List(Person(dani,23), Person(ale,18))

  for (p <- persons if p.age > 20) yield p.name   //> res2: Seq[String] = List(dani)
  // for (s) yield e
  // s is a sequence of generators
  //this is equivalent to

  persons filter (p => p.age > 20) map (p => p.name)
                                                  //> res3: Seq[String] = List(dani)

  //this expressions are equivalent
  (1 until n) flatMap (i => (1 until i) map (j => (i, j))) filter (pair => isPrime(pair._1 + pair._2))
                                                  //> res4: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ), (4,1), (4,3), (5,2), (6,1), (6,5))
  //if you have multiple generators you can use {} instead to have many expressions separated by ;
  for {
    i <- 1 until n
    j <- 1 until n
    if (isPrime(i + j))
  } yield (i, j)                                  //> res5: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,1), (1,2
                                                  //| ), (1,4), (1,6), (2,1), (2,3), (2,5), (3,2), (3,4), (4,1), (4,3), (5,2), (5,
                                                  //| 6), (6,1), (6,5))
  
  def scalarProduct(xs: Vector[Double], ys: Vector[Double]) : Double =
 		(for ( (x,y) <- xs zip ys ) yield x*y).sum
                                                  //> scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double

}