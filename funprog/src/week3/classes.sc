package week3



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
	//This concept is analogous to calls to higher-order functions
}