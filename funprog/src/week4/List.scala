package week4
/** POLYMORPHISM **/
//2 principal forms
// subtyping: instances of a subclass can be passed to a base class (e.g. Cons to List )  For OO programming
// generics: instances of a function or class are created by type parameterization		  For FN programming

//T is a type parameter
trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]

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

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}
//Nothing is a subtype of every type

object List{
  //List(1,2) is a list with 1,2
  //List(1,2) is expanded as List.apply(1,2)
  def apply[T](x1:T, x2:T): List[T] = new Cons(x1, new Cons(x2, new Nil))
  def apply[T] = new Nil
}
