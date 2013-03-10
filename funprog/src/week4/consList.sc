package week4

import week4._
object consList {
  /** TYPE ERASURE **/
  //Type parameter T isn't present  in data structure, so it isn't relevant for evaluation at execution,
  //but it is usued at compile time ( similar to Java, different from C++)

  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
                                                  //> singleton: [T](elem: T)week4.Cons[T]
  singleton[Int](2)                               //> res0: week4.Cons[Int] = 2
  //Type inference
  singleton(1)                                    //> res1: week4.Cons[Int] = 1

  def nth[T](n: Int, xs: List[T]): T = {
    if (xs.isEmpty) throw new IndexOutOfBoundsException
    if (n == 0) xs.head
    else nth(n - 1, xs.tail)
  }                                               //> nth: [T](n: Int, xs: week4.List[T])T

  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : week4.Cons[Int] = 1
  nth(2, list)                                    //> res2: Int = 3
  nth(-1, list)                                   //> java.lang.IndexOutOfBoundsException
                                                  //| 	at week4.consList$$anonfun$main$1.nth$1(week4.consList.scala:15)
                                                  //| 	at week4.consList$$anonfun$main$1.apply$mcV$sp(week4.consList.scala:22)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week4.consList$.main(week4.consList.scala:4)
                                                  //| 	at week4.consList.main(week4.consList.scala)
}