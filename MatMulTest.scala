//
// MatMul tester
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chisel3.iotesters.PeekPokeTester

class MatMulUnitTester(c: MatMul) extends PeekPokeTester(c) {

  def mmul(a: Array[Array[Int]], b: Array[Array[Int]]) : Array[Array[Int]] = {
    for(r <- a) yield {
      for(c <- b.transpose) yield r zip c map Function.tupled(_*_) reduceLeft (_+_)
    }
  }

  def matInit(n: Int) : Array[Array[Int]] = {
    val rseed = 100
    val maxval = 5
    val rnd = new scala.util.Random(rseed)

    Array.tabulate(n){ _ => Array.tabulate(n){_ => rnd.nextInt(maxval+1)+1}}
  }

  def printmat(m: Array[Array[Int]]) {
    m.foreach{r => r.foreach{v => print(f"$v%4d")} ; print(" ;") }
    println()
  }

  val n = c.n
  val a = matInit(n)
  val b = a
  val y = mmul(a,b)

  printmat(a)
  printmat(y)

  def checkresult() : List[Int] = {
    val ret = for(j <- 0 until n*n) yield {
      val out = peek(c.io.out(j))
      print(out + " ")
      out.toInt
    }
    println()
    ret.toList
  }

  for (clk <- 0 until 2*n) {
    for (idx <- 0 until n) {
      val p = clk - idx
      if (p >= 0 && p < n) {
        poke(c.io.in_a(idx), a(idx)(p))
        poke(c.io.in_b(idx), b(p)(idx))
      } else {
        poke(c.io.in_a(idx), 0)
        poke(c.io.in_b(idx), 0)
      }
    }
    step(1)
    print(f"$clk: ")
    checkresult()
  }
  step(1)
  print(f"${2*n}: ")
  val output = checkresult()

  var invalidcnt = 0
  for (i <- output zip y.flatten.toList) {
    if( i._1 != i._2 ) {
      println("Error: " + i._1 + " " + i._2)
      invalidcnt += 1
    }
  }

  if (invalidcnt == 0) println("Verification passed!")
}
