package week3
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