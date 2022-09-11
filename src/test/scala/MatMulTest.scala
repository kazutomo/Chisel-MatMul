//
// SMatMul tester
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chiseltest._
import org.scalatest.Tag
import org.scalatest.flatspec.AnyFlatSpec

class SMatMulSpec extends AnyFlatSpec with ChiselScalatestTester {
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

  def printmat(m: Array[Array[Int]]) : Unit = {
    m.foreach{r => r.foreach{v => print(f"$v%4d")} ; print(" ;") }
    println()
  }

  private def testSMatMul(dut: SMatMul): Unit = {
    val n = dut.n
    val a = matInit(n)
    val b = a
    val y = mmul(a, b)

    printmat(a)
    printmat(y)

    def checkresult() : List[Int] = {
      val ret = for(j <- 0 until n*n) yield {
        val out = dut.io.out(j).peek().litValue
        print(out.toString + " ")
        out.toInt // litValue returns BigInt
      }
      println()
      ret.toList
    }

    for (clk <- 0 until 2*n) {
      for (idx <- 0 until n) {
        val p = clk - idx
        if (p >= 0 && p < n) {
          dut.io.in_a(idx).poke(a(idx)(p))
          dut.io.in_b(idx).poke(b(p)(idx))
        } else {
          dut.io.in_a(idx).poke(0)
          dut.io.in_b(idx).poke(0)
        }
      }
      dut.clock.step(1)
      print(f"$clk: ")
      checkresult()
    }
    dut.clock.step(n-2) //  double check n-2 is correct

    val output = checkresult()

    var invalidcnt = 0
    for (i <- output zip y.flatten.toList) {
      if( i._1 != i._2 ) {
        println("Error: " + i._1 + " " + i._2)
        invalidcnt += 1
      }
    }

    if (invalidcnt == 0) println("Verification passed!")
    dut.clock.step(3)
  }

  "SMatMul basic test" should "pass" in {
    test(new SMatMul())(testSMatMul)
  }
}
