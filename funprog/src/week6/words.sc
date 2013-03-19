package week6

import scala.io.Source

object words {
  val in = Source.fromURL("http://docs.oracle.com/javase/tutorial/collections/interfaces/examples/dictionary.txt")
                                                  //> in  : scala.io.BufferedSource = non-empty iterator

  val words = in.getLines.toList filter (word => word forall (chr => chr.isLetter))
                                                  //> words  : List[String] = List(aa, aah, aahed, aahing, aahs, aal, aalii, aalii
                                                  //| s, aals, aardvark, aardwolf, aargh, aarrgh, aarrghh, aas, aasvogel, ab, aba,
                                                  //|  abaca, abacas, abaci, aback, abacus, abacuses, abaft, abaka, abakas, abalon
                                                  //| e, abalones, abamp, abampere, abamps, abandon, abandons, abapical, abas, aba
                                                  //| se, abased, abasedly, abaser, abasers, abases, abash, abashed, abashes, abas
                                                  //| hing, abasia, abasias, abasing, abatable, abate, abated, abater, abaters, ab
                                                  //| ates, abating, abatis, abatises, abator, abators, abattis, abattoir, abaxial
                                                  //| , abaxile, abba, abbacies, abbacy, abbas, abbatial, abbe, abbes, abbess, abb
                                                  //| esses, abbey, abbeys, abbot, abbotcy, abbots, abdicate, abdomen, abdomens, a
                                                  //| bdomina, abduce, abduced, abducens, abducent, abduces, abducing, abduct, abd
                                                  //| ucted, abductor, abducts, abeam, abed, abele, abeles, abelia, abelian, abeli
                                                  //| as, abelmosk, aberrant, abet, abetment, abets, abettal, abettals, abetted, a
                                                  //| better, abetters, abetti
                                                  //| Output exceeds cutoff limit.

  val mnem = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI",
    '5' -> "JKL", '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")
                                                  //> mnem  : scala.collection.immutable.Map[Char,java.lang.String] = Map(8 -> TUV
                                                  //| , 4 -> GHI, 9 -> WXYZ, 5 -> JKL, 6 -> MNO, 2 -> ABC, 7 -> PQRS, 3 -> DEF)
 
  val charCode: Map[Char, Char] =
    for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit
                                                  //> charCode  : Map[Char,Char] = Map(E -> 3, X -> 9, N -> 6, T -> 8, Y -> 9, J -
                                                  //| > 5, U -> 8, F -> 3, A -> 2, M -> 6, I -> 4, G -> 4, V -> 8, Q -> 7, L -> 5,
                                                  //|  B -> 2, P -> 7, C -> 2, H -> 4, W -> 9, K -> 5, R -> 7, O -> 6, D -> 3, Z -
                                                  //| > 9, S -> 7)

  //maps a word to the digit string
  def wordCode(word: String) = word.toUpperCase map charCode
                                                  //> wordCode: (word: String)String

  wordCode("deca")                                //> res0: String = 3322

  val wordsForNum: Map[String, Seq[String]] = words groupBy wordCode withDefaultValue Seq()
                                                  //> wordsForNum  : Map[String,Seq[String]] = Map(63972278 -> List(newscast), 287
                                                  //| 73337 -> List(burseeds, curseder), 3532748 -> List(fleapit), 2559464 -> List
                                                  //| (allying), 7368363 -> List(pentene), 867328 -> List(unseat), 7868437 -> List
                                                  //| (punties, runtier), 35865397 -> List(flunkeys), 6238767 -> List(maduros), 76
                                                  //| 63537 -> List(poodles), 74965483 -> List(rhyolite), 7626267 -> List(rococos)
                                                  //| , 868437 -> List(tother, unties), 2788476 -> List(brutism), 6428533 -> List(
                                                  //| miauled), 33767833 -> List(deported, deportee), 742533 -> List(picked, ricke
                                                  //| d, shaled, sicked, sickee), 4588387 -> List(gluteus), 46332867 -> List(infec
                                                  //| tor), 7855397 -> List(pulleys), 624627 -> List(magmas), 8747669 -> List(tris
                                                  //| omy), 67846493 -> List(optimize), 44774647 -> List(hissings), 4723837 -> Lis
                                                  //| t(grafter), 6437447 -> List(midship), 86569385 -> List(unjoyful), 33878333 -
                                                  //| > List(detruded), 386583 -> List(evolve), 78475464 -> List(quirking, quislin
                                                  //| g), 746459 -> List(singl
                                                  //| Output exceeds cutoff limit.

  def encode(number: String): Set[List[String]] =
    if (number.isEmpty) Set(List())
    else {
      for {
        split <- 1 to number.length
        word <- wordsForNum(number take split)
        rest <- encode(number drop split)
      } yield word :: rest
    }.toSet                                       //> encode: (number: String)Set[List[String]]

  def translate(number: String): Set[String] =
    encode(number) map (_ mkString " ")           //> translate: (number: String)Set[String]

  translate("7225247386")                         //> res1: Set[String] = Set(sack air fun, pack ah re to, rack ag re un, pa ala 
                                                  //| is duo, pa blahs dun, sab la is fun, sac lags dun, sab jag pe to, sack bis 
                                                  //| fun, pac ka is duo, sack bird un, pack ag re un, sab lag pe um, rack ah pe 
                                                  //| to, pack bi re un, pack ais dun, pa al bi pe un, rack ag re to, pack bise u
                                                  //| n, pac jag re to, pack bird um, pack bird to, pac jag pe um, pa al bi re um
                                                  //| , pa clag re um, sack cire um, pa blah re un, sab jag re um, pa alb ire to,
                                                  //|  sac lag pe um, sab jag pe um, pack cis duo, pac lag pe um, sac jag re un, 
                                                  //| pa al cire un, sack bi re um, sab la ire um, sack ag pe to, rack bi pe un, 
                                                  //| sac ka ire un, sack air duo, pack bi pe un, sack cire to, pac lags fun, sac
                                                  //| k ah pe um, pac lags dun, pa blah re um, pa blahs fun, pac ka ire un, pa al
                                                  //|  ah pe to, pac jag re um, pack bis dun, pa al ais duo, sac jags fun, pa al 
                                                  //| bird um, pa ala ire um, pa al ag pe un, pa blahs duo, pack ag pe um, sack a
                                                  //| g re to, pa clags fun, 
                                                  //| Output exceeds cutoff limit.
}