//
// ProcElem
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chisel3.util.log2Ceil

class ProcElem(val ninbits:Int = 8) extends Module {
    val io = IO(new Bundle {
      val in_h  = Input(UInt(ninbits.W))
      val in_v  = Input(UInt(ninbits.W))
      val out_h = Output(UInt((ninbits*2).W))
      val out_v = Output(UInt((ninbits*2).W))
      val out   = Output(UInt((ninbits*2).W))
    })

  val res  = RegInit(0.U((ninbits*2).W))
  val hreg = RegInit(0.U(ninbits.W))
  val vreg = RegInit(0.U(ninbits.W))

  res := res + (io.in_h * io.in_v)

  hreg := io.in_h
  vreg := io.in_v

  io.out_h := hreg
  io.out_v := vreg
  io.out := res
}
