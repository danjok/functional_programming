package week4

import week3._

/** POLYMORPHISM **/
//2 principal forms
// subtyping: instances of a subclass can be passed to a base class (e.g. Cons to List )  For OO programming
// generics: instances of a function or class are created by type parameterization		  For FN programming

//T is a type parameter
trait List[+T] {
  //we use +T instead of T, because we want co-variance,
  //in fact with Nothing <: T we want also List[Nothing] <: List[T]
  def isEmpty: Boolean
  def head: T
  def tail: List[T]

  //def prepend(elem: T): List[T] = new Cons(elem,this)
  /*
   Problem:
   	elem: T violates variance checking rules ( they prevent mutable operations in covariant classes)
 	Note:
 	
  	val xs : List[IntSet]
  	xs.prepend(Empty)
  
  	val ys : List[NonEmpty]
  	ys.prepend(Empty) //error: Type mismatch
	*/
  //Solution use a lower bound
  //U is a supertype of T
  def prepend[U >: T](elem: U): List[U] = new Cons(elem,this)
  //covariant type parameters may appear in lower bounds of method type parameters
  //contravariant type parameters may appear in upper bounds of method   
  
  override def toString = "" + head
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

//It is the same of previous definition, note that with val you define a field of class
//that is evaluated when object is first initialized ( otherwise with def every time)
//class Cons(_head: Int, _tail: IntList) extends IntList {
//  val head = _head;
//  val tail = _tail;
//}
/*
class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}
*/
//we use an object for optimization issues
object Nil extends List[Nothing] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}
//Nothing is a subtype of every type

object List {
  //List(1,2) is a list with 1,2
  //List(1,2) is expanded as List.apply(1,2)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, Nil))
  def apply[T] = Nil
  //Error: compiler suggests to make List covariant (use +T in declaration of List)
  val x: List[String] = Nil
  
  def f(xs: List[NonEmpty], x: IntSet ) = xs prepend x
  //return type is a IntSet, because it is a supertype of both
  //def f(xs: List[NonEmpty], x: Empty ) = xs prepend x
  //It doesn't work, is the cause that Empty is an object?
}
