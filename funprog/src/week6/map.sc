package week6

object map {
 	val mm = Map("IT"->"Rome","FR"->"Paris")  //> mm  : scala.collection.immutable.Map[java.lang.String,java.lang.String] = Map
                                                  //| (IT -> Rome, FR -> Paris)
 	mm("IT")                                  //> res0: java.lang.String = Rome
 	//mm("UK") throws exception
 	mm.get("UK")                              //> res1: Option[java.lang.String] = None
 	mm.get("IT")                              //> res2: Option[java.lang.String] = Some(Rome)
 	
 	def showName(country: String) = mm.get(country) match{
 		case Some(capital) => capital
 		case None => "missing data"
 	}                                         //> showName: (country: String)java.lang.String
 	
 	showName("IT")                            //> res3: java.lang.String = Rome
	showName("UK")                            //> res4: java.lang.String = missing data
	

	val fruit = List("apple", "pear", "orange", "pinapple")
                                                  //> fruit  : List[java.lang.String] = List(apple, pear, orange, pinapple)
	fruit sortWith(_.length < _.length)       //> res5: List[java.lang.String] = List(pear, apple, orange, pinapple)
	fruit.sorted                              //> res6: List[java.lang.String] = List(apple, orange, pear, pinapple)
	
	fruit groupBy(_.head)                     //> res7: scala.collection.immutable.Map[Char,List[java.lang.String]] = Map(a ->
                                                  //|  List(apple), o -> List(orange), p -> List(pear, pinapple))
	 
  //Map is a partial function, if key doesn't exist it throws an exception
  
  val cap1 = mm withDefaultValue("<unknown>")     //> cap1  : scala.collection.immutable.Map[java.lang.String,java.lang.String] = 
                                                  //| Map(IT -> Rome, FR -> Paris)
  cap1("IT")                                      //> res8: java.lang.String = Rome
   cap1("IT")                                     //> res9: java.lang.String = Rome
}