//
// ProcElem tester
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class ProcElemSpec extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "ProcElem"

  "ProcElem basic test" should "pass" in {
    test(new ProcElem) { dut =>
      require(dut.ninbits > 4)

      val hins = List(1,2,3,0)
      val vins = List(4,5,6,0)
      var acc = 0
      println("ProcElemUnitTester")
      for (i <- vins zip hins) {
        dut.io.out.expect(acc)
        val h = i._1
        val v = i._2
        acc += h * v
        dut.io.in_h.poke(h)
        dut.io.in_v.poke(v)
        val out_h = dut.io.out_h.peek()
        val out_v = dut.io.out_v.peek()
        val out = dut.io.out.peek()
        println(s"h=$h v=$v out_h=$out_h out_v=$out_v out=$out")
        dut.clock.step()
      }
      dut.io.out.expect(acc)
    }
  }
}
