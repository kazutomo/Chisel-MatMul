//
// ProcElem tester
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3.simulator.EphemeralSimulator._
import org.scalatest.flatspec.AnyFlatSpec

class ProcElemSpec extends AnyFlatSpec {
  behavior of "ProcElem"

  private def testProcElem(dut: ProcElem): Unit = {
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
      val out_h = dut.io.out_h.peek().litValue
      val out_v = dut.io.out_v.peek().litValue
      val out = dut.io.out.peek().litValue
      println(s"h=$h v=$v out_h=$out_h out_v=$out_v out=$out")
      dut.clock.step()
    }
    dut.io.out.expect(acc)
  }

  "ProcElem basic test" should "pass" in {
    simulate(new ProcElem())(testProcElem)
  }
}
