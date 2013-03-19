package week7

object streams {
  //Stream is similar to List, but its tail is evaluated only on demand
  
  //Stream is defined using a constant Empty and a constructor
  val streams = Stream.cons(1, Stream.cons(2, Stream.empty))
                                                  //> streams  : Stream.Cons[Int] = Stream(1, ?)
  
  //you can use object Stream as a factory
  
  val xs = Stream(1,2,3,4)                        //> xs  : scala.collection.immutable.Stream[Int] = Stream(1, ?)
	(1 to 100) toStream                       //> res0: scala.collection.immutable.Stream[Int] = Stream(1, ?)

	val x=0                                   //> x  : Int = 0
	//concatenation for stream
	x #:: xs                                  //> res1: scala.collection.immutable.Stream[Int] = Stream(0, ?)
	
	//note in cons of Stream, tail is a call by name parameter, so evaluation is delayed
	//and it is evaluated only on demand
	//def cons[T](hd:T, tl:=> Stream[T])
	
	//lazy evaluation
	//by default Scala uses strict evaluation
	//in purely functional programming doesn't matter when execution happens and how much space takes
	//but in Scala there are also mutuable data structures, and so side effect
	
	lazy val a = 3                            //> a  : Int = <lazy>
	a                                         //> res2: Int = 3
	
	
	def expr = {
		val x = { println("x"); 1}
		lazy val y = { println("y"); 2}
		def z = {println("z");3}
		z+y+x+z+y+x
	}                                         //> expr: => Int
	expr                                      //> x
                                                  //| z
                                                  //| y
                                                  //| z
                                                  //| res3: Int = 12
  //so in Stream implementation of cons, tail must be of type lazy
  
  //infinite Stream
  //evaluated only on demand
  def from(n:Int): Stream[Int] = n #::from(n+1)   //> from: (n: Int)Stream[Int]
  
  val nats= from(0)                               //> nats  : Stream[Int] = Stream(0, ?)
 	//multiple of 4
 	val m4s = nats map(_*4)                   //> m4s  : scala.collection.immutable.Stream[Int] = Stream(0, ?)
 	
 	(m4s take 100).toList                     //> res4: List[Int] = List(0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52,
                                                  //|  56, 60, 64, 68, 72, 76, 80, 84, 88, 92, 96, 100, 104, 108, 112, 116, 120, 
                                                  //| 124, 128, 132, 136, 140, 144, 148, 152, 156, 160, 164, 168, 172, 176, 180, 
                                                  //| 184, 188, 192, 196, 200, 204, 208, 212, 216, 220, 224, 228, 232, 236, 240, 
                                                  //| 244, 248, 252, 256, 260, 264, 268, 272, 276, 280, 284, 288, 292, 296, 300, 
                                                  //| 304, 308, 312, 316, 320, 324, 328, 332, 336, 340, 344, 348, 352, 356, 360, 
                                                  //| 364, 368, 372, 376, 380, 384, 388, 392, 396)
  def sieve(s:Stream[Int]): Stream[Int]=
  	s.head #::sieve(s.tail filter (_%s.head!=0))
                                                  //> sieve: (s: Stream[Int])Stream[Int]
 val primes = sieve(from(2))                      //> primes  : Stream[Int] = Stream(2, ?)
 primes take(50) toList                           //> res5: List[Int] = List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 
                                                  //| 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 1
                                                  //| 31, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 2
                                                  //| 11, 223, 227, 229)
 def sqrtStream(x:Double): Stream[Double] = {
 	def improve(guess:Double)= (guess+x/guess)/2
 	//infinite recursion is avoided by lazy evaluation
 	lazy val guesses: Stream[Double] = 1 #::(guesses map improve)
 	guesses
 }                                                //> sqrtStream: (x: Double)Stream[Double]
 sqrtStream(4).take(10).toList                    //> res6: List[Double] = List(1.0, 2.5, 2.05, 2.000609756097561, 2.000000092922
                                                  //| 2947, 2.000000000000002, 2.0, 2.0, 2.0, 2.0)
 
 def isGoodEnough(guess:Double,x:Double)=
 	math.abs( (guess*guess-x)/x)<0.0001       //> isGoodEnough: (guess: Double, x: Double)Boolean
 
 //we have all solutions that are good
 sqrtStream(4).filter(isGoodEnough(_,4)).take(10).toList
                                                  //> res7: List[Double] = List(2.0000000929222947, 2.000000000000002, 2.0, 2.0, 
                                                  //| 2.0, 2.0, 2.0, 2.0, 2.0, 2.0)
	//infinite stream of multiples of N
	val N =3                                  //> N  : Int = 3
	val ys1 = from(1) map (_*N)               //> ys1  : scala.collection.immutable.Stream[Int] = Stream(3, ?)
	val ys2 = from(1) filter (_%N==0)         //> ys2  : scala.collection.immutable.Stream[Int] = Stream(3, ?)
	//ys1 is more efficient than ys2 because it doesn't generate values that then are filtered
}