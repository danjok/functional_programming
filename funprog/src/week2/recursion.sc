package week2

object exercise {

  /**
   * Recursion
   */
  //pattern for factorial of n
  //using tail recursion
  def factorial(n: Int): Int = {
    def loop(acc: Int, n: Int): Int =
      if (n == 0) acc
      else loop(acc * n, n - 1)
    loop(1, n)
  } //> factorial: (n: Int)Int
  factorial(4) //> res0: Int = 24

  //pattern for sum of f(i) with i from a to b
  //using linear recursion
  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f, a + 1, b) //> sum: (f: Int => Int, a: Int, b: Int)Int

  //higher order functions
  def sumInts(a: Int, b: Int): Int = sum(id, a, b) //> sumInts: (a: Int, b: Int)Int
  def sumCubes(a: Int, b: Int): Int = sum(cube, a, b)
  //> sumCubes: (a: Int, b: Int)Int
  //first order functions
  def id(x: Int) = x //> id: (x: Int)Int
  def cube(x: Int) = x * x * x //> cube: (x: Int)Int

  //higher order functions with anonymous first order functions
  def sumCubes2(a: Int, b: Int): Int = sum(x => x * x * x, a, b)
  //> sumCubes2: (a: Int, b: Int)Int
  def sumInts2(a: Int, b: Int): Int = sum(x => x, a, b)
  //> sumInts2: (a: Int, b: Int)Int

  sumInts2(4, 5) //> res1: Int = 9
  sumCubes2(2, 3) //> res2: Int = 35

  //anonymous function
  (x: Int) => x * x * x //> res3: Int => Int = <function1>

  //pattern for sum of f(i) with i from a to b
  //using tail recursion
  def sum2(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int =
      if (a > b) acc
      else loop(a + 1, acc + f(a))
    loop(a, 0)
  } //> sum2: (f: Int => Int, a: Int, b: Int)Int

  sum2(x => x, 7, 6) //> res4: Int = 0

  /**
   * Currying
   */
  //	sum3 is a function with
  //		params a function f: Int=>Int
  //		returns a function (Int,Int) => Int
  def sum3(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF
  } //> sum3: (f: Int => Int)(Int, Int) => Int

  def sumInts3 = sum3(x => x) //> sumInts3: => (Int, Int) => Int
  def sumCubes3 = sum3(x => x * x * x) //> sumCubes3: => (Int, Int) => Int
  sumInts3(3, 4) //> res5: Int = 7
  sumCubes3(2, 3) //> res6: Int = 35
  //function application associates to the left
  sum3(x => x * x * x)(2, 3) //> res7: Int = 35

  //Multiple parameter lists (MPL)
  def sum4(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum4(f)(a + 1, b) //> sum4: (f: Int => Int)(a: Int, b: Int)Int
  //type of this function is (Int=>Int)=>(Int,Int)=>Int
  //functional types associate to the right
  // Int =>Int =>Int means Int => (Int =>Int)
  //Using MPL you can prepare fucntion to use then
  def func = sum4(cube)_ //> func: => (Int, Int) => Int
  func(2, 3) //> res8: Int = 35
}