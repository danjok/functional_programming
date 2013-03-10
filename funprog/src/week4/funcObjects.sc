package week4

object funcObjects {

  //Functions are object with apply methods
  trait Function1[A, B] {
    def apply(x: A): B
  }

  //anoymous function like an object
  {
    class AnonFun extends Function1[Int, Int] {
      def apply(x: Int) = x * x
    }
    new AnonFun
  }                                               //> res0: AnonFun = week4.funcObjects$$anonfun$main$1$AnonFun$1@485329c5

  //or
  //anonymous class syntax
  val f = new Function1[Int, Int] {
    def apply(x: Int) = x * x
  }                                               //> f  : java.lang.Object with week4.funcObjects.Function1[Int,Int] = week4.func
                                                  //| Objects$$anonfun$main$1$$anon$1@35549f94
  f.apply(7)                                      //> res1: Int = 49
	val ff = (x:Int) => x*x                   //> ff  : Int => Int = <function1>
	ff(7)                                     //> res2: Int = 49
	
	//eta-expansion: converts a function fun in an anonymous function
	//and then in an object with apply method
	def fun(x:Int): Boolean = x > 0           //> fun: (x: Int)Boolean
	(x:Int) => fun(x)                         //> res3: Int => Boolean = <function1>
	
	new Function1[Int, Boolean] {
    def apply(x: Int) = fun(x)
  }                                               //> res4: java.lang.Object with week4.funcObjects.Function1[Int,Boolean] = week4
                                                  //| .funcObjects$$anonfun$main$1$$anon$2@159b5217
}