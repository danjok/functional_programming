package week6

object nqueens {

  def queens(n: Int): Set[List[Int]] = {

    def isSafe(col: Int, queens: List[Int]): Boolean = {
      val row = queens.length
      //to generate pairs of row,col
      //List(0,3,1) -> List ( (2,0),(1,3),(0,1) )
      val queensWithRow = (row - 1 to 0 by -1) zip queens
     	queensWithRow forall {
        case (r, c) => col != c && math.abs(col-c) != row-r
        //to avoid to put a queen on the same column or on the same diagonal
        
      }
    }
    
		//suppose to have already generated all solutions
		//consisting of placing k-1 queens on board of size n
		//each sol is represented by a list of length k-1 containing the number of columns
		//the index of list represents the row in reverse order
		//to place kth queen we generate all posssible extensions of each sol preceeded by a new queen
    def placeQueens(k: Int): Set[List[Int]] =
      if (k == 0) Set(List())
      else
        for {
        	//we have already sol for k-1
        	queens <- placeQueens(k - 1)
          //we try for all columns
          col <- 0 until n
          if (isSafe(col, queens))
        } yield col :: queens
    placeQueens(n)
  }                                               //> queens: (n: Int)Set[List[Int]]
  
  //method to print cheessboard
  def show(queens: List[Int]) = {
  	val lines =
  	for (col <- queens.reverse )
  	yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
  	"\n" + (lines mkString "\n")
  }                                               //> show: (queens: List[Int])java.lang.String
  
  (queens(4) map show) mkString "\n"              //> res0: String = "
                                                  //| * * X * 
                                                  //| X * * * 
                                                  //| * * * X 
                                                  //| * X * * 
                                                  //| 
                                                  //| * X * * 
                                                  //| * * * X 
                                                  //| X * * * 
                                                  //| * * X * "
}