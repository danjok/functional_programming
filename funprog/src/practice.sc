object practice {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  var arr = new Array[Int](5)                     //> arr  : Array[Int] = Array(0, 0, 0, 0, 0)
  //Factory Method
  var lst = List(1, 2, 3)                         //> lst  : List[Int] = List(1, 2, 3)

  val list = List(1, 2, 3)                        //> list  : List[Int] = List(1, 2, 3)
  list.foreach(println) // same                   //> 1
                                                  //| 2
                                                  //| 3
  //Closure function
  list.map(x => println(x))                       //> 1
                                                  //| 2
                                                  //| 3
                                                  //| res0: List[Unit] = List((), (), ())
  list.map(println)                               //> 1
                                                  //| 2
                                                  //| 3
                                                  //| res1: List[Unit] = List((), (), ())
  list.map(x => x + 2)                            //> res2: List[Int] = List(3, 4, 5)
  list.map(_ + 2)                                 //> res3: List[Int] = List(3, 4, 5)
  list.filter(x => x % 2 == 1)                    //> res4: List[Int] = List(1, 3)
  list.filter(_ % 2 == 1)                         //> res5: List[Int] = List(1, 3)
  list.reduce((x, y) => x + y) // => 6            //> res6: Int = 6
  list.reduce(_ + _)                              //> res7: Int = 6
  
}