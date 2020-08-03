//
// Pcnt4 tester
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chisel3.iotesters.PeekPokeTester

class PcntUnitTester(c: Pcnt) extends PeekPokeTester(c) {

  for (i <- 0 until 10) {
    poke(c.io.in_a, i)
    poke(c.io.in_b, 0)
    val out = peek(c.io.out)
    println(s"i=$i out=$out")
    step(1)
  }
}
