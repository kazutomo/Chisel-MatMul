//
// A processing element for systolic-based matrix multipliation
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chisel3.util.log2Ceil

// each ProcElem (PE) is mapped to each element in a NxN output matrix
class ProcElem(val ninbits:Int = 8) extends Module {
    val io = IO(new Bundle {
      // input from horizontal direction
      val in_h  = Input(UInt(ninbits.W))
      // input from vertical direction
      val in_v  = Input(UInt(ninbits.W))
      // output to horizontal direction
      val out_h = Output(UInt((ninbits*2).W))
      // output to vertical direction
      val out_v = Output(UInt((ninbits*2).W))
      // the result after N cycles once this receives the first actual data
      val out   = Output(UInt((ninbits*2).W))
    })

  val res  = RegInit(0.U((ninbits*2).W))
  val hreg = RegInit(0.U(ninbits.W))
  val vreg = RegInit(0.U(ninbits.W))

  // this is the main computation part
  res := res + (io.in_h * io.in_v)

  // inputs are delayed one cycle to next PEs
  hreg := io.in_h
  vreg := io.in_v

  io.out_h := hreg
  io.out_v := vreg
  io.out := res
}

// generates Verilog code
import chisel3.stage.ChiselStage

object ProcElemDriver extends App {
  (new ChiselStage).emitVerilog(new ProcElem())
}
