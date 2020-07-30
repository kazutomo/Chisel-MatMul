//
// A 4-bit pop count (not parameterized)
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chisel3.util.log2Ceil

class Pcnt() extends Module {
    val io = IO(new Bundle {
      val in_a  = Input(UInt(4.W))
      val in_b  = Input(UInt(4.W))
      val out   = Output(SInt(8.W))
    })

  val stbl = Array(
    -4, -2, -2, 0, -2, 0, 0, 2,
    -2,  0,  0, 2,  0, 2, 2, 4)

  val lut = VecInit(stbl map {s => s.S(8.W)})

  io.out := lut(~(io.in_a ^ io.in_b))
}
