package week6

object queries {
  case class Book(title: String, authors: List[String])

  val books: List[Book] = List(
    Book(title = "T1",
      authors = List("A1", "A2")),
    Book(title = "T2",
      authors = List("A1", "A3")))                //> books  : List[week6.queries.Book] = List(Book(T1,List(A1, A2)), Book(T2,List
                                                  //| (A1, A3)))

  for (b <- books; a <- b.authors if a startsWith ("A")) yield a
                                                  //> res0: List[String] = List(A1, A2, A1, A3)
  //find authors that wrote at least 2 books
  for {
    b1 <- books
    b2 <- books
    if b1 != b2
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1                                      //> res1: List[String] = List(A1, A1)

  //to avoid to have 2 times the same result
  for {
    b1 <- books
    b2 <- books
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1                                      //> res2: List[String] = List(A1)

  //if an author wrote 3 books
  val books2 = Book(title = "T3",
    authors = List("A1")) :: books                //> books2  : List[week6.queries.Book] = List(Book(T3,List(A1)), Book(T1,List(A1
                                                  //| , A2)), Book(T2,List(A1, A3)))
  
	for {
    b1 <- books2
    b2 <- books2
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1                                      //> res3: List[String] = List(A1, A1, A1)

  //sol to this problem
  /*
  {
    for {
      b1 <- books2
      b2 <- books2
      if b1.title < b2.title
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1
  }.distinct
	*/
	
	//another solution is to use a different data structure
	//such as a SET
}