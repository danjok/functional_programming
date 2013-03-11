package week4

object exprs {
  def show(e:Expr) : String = e match {
    case Number(n) => n.toString
    case Sum(l, r) => show(l)+" + "+show(r)
  }                                               //> show: (e: week4.Expr)String
  
  show(Sum(Number(1), Number(46)))                //> res0: String = 1 + 46
  Sum(Number(1), Number(46)).eval                 //> res1: Int = 47
}