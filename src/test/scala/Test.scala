//
// matmul test driver
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3.iotesters
import chisel3.iotesters.{Driver, PeekPokeTester}
import chisel3.stage.ChiselStage

object TestMain extends App {

  // component target list.
  val targetlist = List(
    "pe", "mm"
  )

  val a = if (args.length > 0) args(0) else "mm"
  val tmp = a.split(":")

  val target = tmp(0)
  val mode = if (tmp.length > 1) tmp(1) else "test"

  mode match {
    case "test" => {}
    case "verilog" => {}
    case _ => println(f"Warning: $mode is not a valid mode")
  }

  val n = 8
  val ninbits = 8
  println(f"MODE=$mode TARGET=$target N=$n")

  target match {

    case "list" =>
      println("*target list")
      for (t <- targetlist)  println(t)

    case "pe" =>
      mode match {
        case "verilog" =>
          (new ChiselStage).emitVerilog(new ProcElem())
      }

    case _ =>
      mode match {
        case "verilog" =>
          (new ChiselStage).emitVerilog(new MatMul(n, ninbits))
      }
  }
}
