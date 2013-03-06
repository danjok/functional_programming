package week3

import scala.reflect.Super

object classes {
  val t1 = new NonEmpty(3, Empty, Empty)          //> t1  : week3.NonEmpty = {.3.}
  val t2 = t1 incl 4                              //> t2  : week3.IntSet = {.3{.4.}}
  val t3 = t2 incl 2 incl 1 incl 6 incl 8         //> t3  : week3.IntSet = {{{.1.}2.}3{.4{.6{.8.}}}}

  val t4 = new NonEmpty(7, Empty, Empty) incl 5 incl 10 incl 9 incl 13
                                                  //> t4  : week3.IntSet = {{.5.}7{{.9.}10{.13.}}}
  t3 union t4                                     //> ---
                                                  //| Elem 3
                                                  //| Left{{.1.}2.}
                                                  //| Right{.4{.6{.8.}}}
                                                  //| Other{{.5.}7{{.9.}10{.13.}}}
                                                  //| ---
                                                  //| Elem 2
                                                  //| Left{.1.}
                                                  //| Right.
                                                  //| Other{.4{.6{.8.}}}
                                                  //| ---
                                                  //| Elem 1
                                                  //| Left.
                                                  //| Right.
                                                  //| Other.
                                                  //| ---
                                                  //| Elem 1
                                                  //| Left.
                                                  //| Right.
                                                  //| Other{.4{.6{.8.}}}
                                                  //| ---
                                                  //| Elem 4
                                                  //| Left{.1{.2.}}
                                                  //| Right{.6{.8.}}
                                                  //| Other{{.5.}7{{.9.}10{.13.}}}
                                                  //| ---
                                                  //| Elem 1
                                                  //| Left.
                                                  //| Right{.2.}
                                                  //| Other{.6{.8.}}
                                                  //| ---
                                                  //| Elem 2
                                                  //| Left.
                                                  //| Right.
                                                  //| Other{.6{.8.}}
                                                  //| ---
                                                  //| Elem 6
                                                  //| Left{{.1.}2.}
                                                  //| Right{.8.}
                                                  //| Other{{.5.}7{{.9.}10{.13.}}}
                                                  //| ---
                                                  //| Elem 2
                                                  //| Left{.1.}
                                                  //| Right.
                                                  //| Other{.8.}
                                                  //| ---
                                                  //| Elem 1
                                                  //| Left.
                                                  //| Right.
                                                  //| Other.
                                                  //| ---
                                                  //| Elem 1
                                                  //| Left.
                                                  //| Right.
                                                  //| Other{.8.}
                                                  //| ---
                                                  //| Elem 8
                                                  //| Left{.1{.2.}}
                                                  //| Right.
                                                  //| Other{{.5.}7{{.9.}10{.13.}}}
                                                  //| ---
                                                  //| Elem 1
                                                  //| Left.
                                                  //| Right{.2.}
                                                  //| Other.
                                                  //| ---
                                                  //| Elem 2
                                                  //| Left.
                                                  //| Right.
                                                  //| Other.
                                                  //| ---
                                                  //| Elem 2
                                                  //| Left{.1.}
                                                  //| Right.
                                                  //| Other{{.5.}7{{.9.}10{.13.}}}
}

/**
 * *
 * IntSet
 * *
 */
//Persistent data structure
//at every insert of a node
//the part of tree in which you insert the new value is replciated
//so the previous tree is maintained

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else new NonEmpty(elem, left, right incl x)

  override def toString = "{" + left + elem + right + "}"

  def union(other: IntSet): IntSet = {
    println("-" * 3)
    println("Elem " + elem)
    println("Left" + left)
    println("Right" + right)
    println("Other" + other)

    ((left union right) union other) incl elem
    //Why? see Lecture 3.4 Class Hierarchies
  }
}
/*
class Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def toString = "."
}
*/

//Instead of using a class for empty set we can use a Object
//that is a singleton object
//Objects are value, so they are evaluated at the first reference
object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  override def toString = "."
  def union(other: IntSet): IntSet = other
}

/**
 * *
 * SUPER CLASS and SUB CLASS
 * *
 */
abstract class Base {
  def foo = 1
  def bar: Int
}
class Sub extends Base {
  //if you define a method that is defined in the superclass
  //you have to use override
  override def foo = 2
  def bar = 3

  /**
   * *
   * DYNAMIC BINDING
   * *
   */
	//dynamic method dispatch:
	//the code invoked by a method call depends on the runtime type of the object containing the object
}