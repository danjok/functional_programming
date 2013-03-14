package week5

object merge_pairs {
 //Pair
 val pair = ("answer", 42)                        //> pair  : (java.lang.String, Int) = (answer,42)
 //pattern matching
 val (label,number)=pair                          //> label  : java.lang.String = answer
                                                  //| number  : Int = 42
  
 def msort(xs:List[Int]) : List[Int] = {
 	val n = xs.length/2
 	if (n == 0) xs
 	else {
 		def merge(xs:List[Int], ys:List[Int]) : List[Int] = (xs,ys) match {
 			//xs is empty
 			case (Nil,ys) => ys
 			//ys is empty
 			case (xs,Nil) => xs
 			//both xs and ys have at least one element
 			case (x::xs1, y::ys1) =>
 				if (x<y) x::merge(xs1,ys)
 				else y::merge(xs,ys1)
 				//x is the first elem of list xs and xs1 is tail of xs
 		}
 		val (fst,snd) = xs splitAt n
 		merge(msort(fst), msort(snd))
 	}
 }                                                //> msort: (xs: List[Int])List[Int]
 
 val nums = List(2,4,5,-1,7)                      //> nums  : List[Int] = List(2, 4, 5, -1, 7)
 //split list at index n
 nums.splitAt(2)                                  //> res0: (List[Int], List[Int]) = (List(2, 4),List(5, -1, 7))
 
 msort(nums)                                      //> res1: List[Int] = List(-1, 2, 4, 5, 7)
}