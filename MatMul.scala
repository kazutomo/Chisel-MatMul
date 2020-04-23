//
// MatMul
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
//
package matmul

import chisel3._
import chisel3.util.log2Ceil

// Compute A * B, where A and B are a square matrix.
//
class MatMul(val n:Int = 3, val ninbits:Int = 8) extends Module {
  val io = IO(new Bundle {
    val in_a  = Input(Vec(n, UInt(ninbits.W))) // horizontal inputs
    val in_b  = Input(Vec(n, UInt(ninbits.W))) // vertical inputs
    val out   = Output(Vec(n*n, UInt((ninbits*2).W)))
  })

  val p_elems = VecInit(Seq.fill(n*n) { Module(new ProcElem(ninbits)).io })
  val h_wires = Wire(Vec((n-1)*n, UInt(ninbits.W)))
  val v_wires = Wire(Vec(n*(n-1), UInt(ninbits.W)))

  def gethidx(r: Int, c: Int) : Int = r*(n-1) + c  // last column is terminated
  def getvidx(r: Int, c: Int) : Int = r*n + c

  // connecting PEs in a systolic manner
  for (col <- 0 until n) {
    for (row <- 0 until n) {
      val pidx = row*n + col
      io.out(pidx) := p_elems(pidx).out // results

      // wiring up PEs
      // horizontal inputs
      if (col == 0)   p_elems(pidx).in_h := io.in_a(row)
      else            p_elems(pidx).in_h := h_wires(gethidx(row,col-1))
      // horizontal outputs to next PEs
      if (col < n-1)  h_wires(gethidx(row,col)) := p_elems(pidx).out_h
      // vertical inputs
      if (row == 0)   p_elems(pidx).in_v := io.in_b(col)
      else            p_elems(pidx).in_v := v_wires(getvidx(row-1,col))
      // vertical outputs to next PEs
      if (row < n-1)  v_wires(getvidx(row,col)) := p_elems(pidx).out_v
    }
  }
}
