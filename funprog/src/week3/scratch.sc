package week3

import week3.Rational
//wildcard import
//import week3._

object scratch {
  new Rational(1, 2)                              //> res0: week3.Rational = 1/2
	def v = new Square()                      //> v: => week3.Square
	v.move                                    //> res1: Boolean = false
	v.height                                  //> res2: Int = 0
	
	def error(msg:String) = new Error(msg)    //> error: (msg: String)java.lang.Error
	error("end")                              //> res3: java.lang.Error = java.lang.Error: end
	val x = null                              //> x  : Null = null
	
	if (true) 1 else false                    //> res4: AnyVal = 1
	if (true) "1" else false                  //> res5: Any = 1
	if (true) 1 else 0                        //> res6: Int = 1
}
/**
 * **
 * TRAIT
 * **
 */
//single inheritance, if you need to inherit from more entity you have to use trait
//Trait are similar to interface in java, but they have also fields and concrete methods
//no

trait Planar {
  def height: Int = 0
  def width: Int = 0
  def surface = height * width
}

trait Movable {
  def move: Boolean = false
}

abstract class Shape{
	def numVertex: Int
}


class Square extends Shape with Planar with Movable{
	def numVertex = 4
}