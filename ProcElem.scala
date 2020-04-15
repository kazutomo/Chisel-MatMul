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
      val in  = Input(UInt(ninbits.W))
      val out = Output(UInt(ninbits.W))
    })

  io.out := io.in
}
