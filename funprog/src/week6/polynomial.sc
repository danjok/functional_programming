package week6

object polynomial {
  val a = Map(1->2)                               //> a  : scala.collection.immutable.Map[Int,Int] = Map(1 -> 2)
  //override previous key-value pair
  a + (1->3) //add an element                     //> res0: scala.collection.immutable.Map[Int,Int] = Map(1 -> 3)
  a ++ Map(1->4) //union between maps             //> res1: scala.collection.immutable.Map[Int,Int] = Map(1 -> 4)
  
  class Poly(val terms0: Map[Int,Double]) {
  	//Arbitrary number of arguments
  	def this(bindings: (Int,Double)*) = this(bindings.toMap)
  	//thanks to withDefaultValue we haven't exception
  	val terms = terms0 withDefaultValue 0.0
  	
  	//with only the ++ you add values into the Map
  	//def +(that: Poly)= new Poly(terms ++ (that.terms map adjust))
  	def adjust(term: (Int,Double)) : (Int,Double) = {
  		val (exp,coeff) = term
  		exp -> (coeff+terms(exp))
  	}
  	/*
  	def adjust(term: (Int,Double)) : (Int,Double) = {
  		val (exp,coeff) = term
  		terms.get(exp) match {
  			case Some(coeff1) => exp -> (coeff+coeff1)
  			case None => exp-> coeff
  		}
  	}
  	*/
  	//this version of + is more efficient becasue it doesn't compute an
  	//intermediate list as before
  	def +(that: Poly)= new Poly( (that.terms foldLeft terms )(addTerm) )
  	def addTerm(terms: Map[Int,Double], term: (Int,Double) ): Map[Int,Double] ={
  		val (exp,coeff) = term
  		terms + (exp -> (coeff+terms(exp)))
  	
  	}
  	override def toString = {
  		//you have to order the values
  		(for ( (exp,coeff) <-terms.toList.sorted.reverse ) yield coeff+"x^"+exp) mkString " + "
  	}
  }
  
  val p1 = new Poly(1->2.0, 3->4.0, 5->6.2)       //> p1  : week6.polynomial.Poly = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p2 = new Poly(Map(0->3.0, 3->7.0))          //> p2  : week6.polynomial.Poly = 7.0x^3 + 3.0x^0
  
  p1 + p2                                         //> res2: week6.polynomial.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  p1.terms(7)                                     //> res3: Double = 0.0
 
}