package week4

import week3._
import week4._
object typeBounds {
  /**
   * *
   * TYPE BOUNDS
   * *
   */
  /*
	assertAllPolls
 	takes an IntSet
 	returns the IntSet itself if all its elements are positive
 	throws an exception otherwise
 	
 	def assertAllPolls(s: IntSet): IntSet = ...
	
	It isn't very precise, because assertAllPolls can also returns an Empty set
	If we want to express that assertAllPolls takes an Empty sets to Empty sets and NonEmpty sets to NonEmpty sets, we use
	
	def assertAllPolls[S <: IntSet](r:S): S = ...
	
	It means that S can be instantiated only to types that conform to IntSet, because "<: IntSet" is an Upper Bound
	of type parameter S
	
	S <: T means "S is a subtype of T"
	S >: T means "S is a supertype of T"
	(S type set is greater than T type set)
	*/
  //Mixed Bounds
  def assertAllPolls[S >: NonEmpty <: IntSet](r: S): S = r
                                                  //> assertAllPolls: [S >: week3.NonEmpty <: week3.IntSet](r: S)S

  /**
   * *
   * COVARIANCE
   * *
   */
  //Interaction between subtyping and type parameters
  /*
	NonEmpty <: IntSet
	is
	List[NonEmpty] <: List[IntSet] ?
	A List of NonEmpty elements is a special case of a List of arbitrary set
	We call types for which this relationship holds COVARIANT,
	because their subtyping relationship varies with the type parameter
	
	LISKOV SUBSTITUTION PRINCIPLE
	if A <: B (A is a subtype of B)
	then everything one can do with a value of type B
	one should also be able to do with a value of type A
	*/

  val a: Array[NonEmpty] = Array(new NonEmpty(1, Empty, Empty))
                                                  //> a  : Array[week3.NonEmpty] = Array({.1.})
  val bb: Array[IntSet] = Array(new NonEmpty(2, Empty, Empty))
                                                  //> bb  : Array[week3.IntSet] = Array({.2.})
  /*
  val b: Array[IntSet] = a //Error: class Array is invariant in type T
   
  b(0) = Empty
  val s: NonEmpty = a(0)
	*/
  /*
	A type that accepts mutations of its elements should not be covariant
	Immutable type can be covariant, if some conditions on methods are met
	(covariant) List is immutable
	(not covariant) Array is mutable, you can update value
	
	with A<:B														Definitions
	C[A] <: C[B]	covariant 						class C[+A]{ ... }
	C[A] >: C[B] 	contravariant					class C[-A]{ ... }
	otherwise	  	nonvariant						class C[A]{ ... }
	
	*/
  type B = NonEmpty => IntSet
  //				 /\					\/
  type A = IntSet => NonEmpty
  //we can pass the same arguments of type B to type A
  /*
  According to Liskow substitution principle A <: B
  because if you can give as input to both function types an NonEmpty
  and obtain a IntSet for B or a NonEmpty for A, so A <:B
  
  In general, if A2<:A1 and B1<:B2
  						then A1=>B1 <: A2=>B2
  that is:
  A2 => B2
	/\		\/
  A1 => B1
  */
  
  //functions are contravariant in arguments types
  //							covariant in result types
  //revision of function trait declaration
  trait Function1[-T, +U]{
  	def apply(x:T): U
  }
  
  //Problematic combination
  //if we turn an Array in a class
  class Array2[+T]{
  	//def update(x:T) ={} //Error: covariant type T occurs in contravariant position in type T of value x
  	//function arguments must be contravariant
  }
  
  //covariant type parameter T appears in paramter position of method update
  
}