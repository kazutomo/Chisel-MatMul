//
// binary matrix multiply
//
// popcnt(xnor(A, B))
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chisel3.util.log2Ceil

class Pcnt(val n:Int = 4) extends Module {
    val io = IO(new Bundle {
      val in_a  = Input(UInt(n.W))
      val in_b  = Input(UInt(n.W))
      val out   = Output(UInt((log2Ceil(n)+1).W))
    })

  val cz4 = Module(new Clz4())

  //cz4.io.in := ~(io.in_a ^ io.in_b)
  cz4.io.in := io.in_a | io.in_b

  io.out := cz4.io.out
}
