//
// MatMul tester
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chisel3.iotesters.PeekPokeTester

class MatMulUnitTester(c: MatMul) extends PeekPokeTester(c) {
  poke(c.io.in, 10)
  val out = peek(c.io.out)
  println(s"ProcElem: $out")
}
