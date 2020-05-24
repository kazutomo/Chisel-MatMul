//
// ProcElem tester
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chisel3.iotesters.PeekPokeTester

class ProcElemUnitTester(c: ProcElem) extends PeekPokeTester(c) {

  val hins = List(1,2,3,0)
  val vins = List(4,5,6,0)

  for (i <- vins zip hins) {
    val h = i._1
    val v = i._2
    poke(c.io.in_h, h)
    poke(c.io.in_v, v)
    val out_h = peek(c.io.out_h)
    val out_v = peek(c.io.out_v)
    val out = peek(c.io.out)

    println(s"h=$h v=$v out_h=$out_h out_v=$out_v out=$out")

    step(1)
  }
}
