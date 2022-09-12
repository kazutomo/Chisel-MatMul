A Chisel simple systolic matrix multiply generator

This is an NxN systolic-array matrix multiplication design written in
Chisel3. The systolic array matrix multiplication computes a NxN
matrix multiplication in O(N), while the computational complexity of
the navive implementation and the best known software algorithm are
O(N^3) and O(N^2.37), respectively.

The current version only supports square matrices and NxN processing
unites needs to be inferred (e.g., 3x3 matrix requires 9 process
units). This is a straightforward spacial implementation thus N is
limited by the available resource of a target device or a simulator.

# Prerequisites
- Java SDK
- sbt (see https://www.scala-sbt.org/download.html for installation)
- (optional) Verilator, Scala

Getting Started
---------------

     $ git clone https://github.com/kazutomo/Chisel-MatMul.git
     $ cd Chisel-MatMul
     $ make test

NOTE:
- tested on Fedora 35 with sbt 1.7.1


List test benches and run a specific test bench
--------------

     $ sbt "show Test/definedTestNames"
	 [info] ...
     [info] * matmul.ProcElemSpec
     [info] * matmul.SMatMulSpec

     # Use Treadle (Scala-based simulator)
     $ sbt "testOnly matmul.ProcElemSpec -- -n RequiresTreadle"
     ProcElemUnitTester
     ....
     [info] All tests passed.

	 # Use Verilator
     $ sbt "testOnly matmul.SMatMulSpec -- -n RequiresVerilator"
     ...
     ...
        2   5   5 ;   1   2   1 ;   3   3   2 ;[info] [0.012]
       24  35  25 ;   7  12   9 ;  15  27  22 ;[info] [0.012]
     0: 4 0 0 0 0 0 0 0 0 [info] [0.016]
     1: 9 10 0 2 0 0 0 0 0 [info] [0.016]
     2: 24 20 10 4 5 0 6 0 0 [info] [0.017]
     3: 24 35 15 7 9 5 9 15 0 [info] [0.018]
     4: 24 35 25 7 12 7 15 21 15 [info] [0.019]
     5: 24 35 25 7 12 9 15 27 18 [info] [0.019]
     24 35 25 7 12 9 15 27 22 [info] [0.020]
     [info] [0.025] Verification passed!
     test MatMul Success: 0 tests passed in 15 cycles in 0.045418 seconds 330.27 Hz
     [info] [0.026] RAN 10 CYCLES PASSED
     [success] Total time: 4 s, completed Apr 22, 2020 10:11:36 PM

NOTE: Without any scalatest argument (-n or -l), both Treadle and
Verilator will run.


List Verilog generator and generate a specific module
--------------

     $ sbt "show Compile/discoveredMainClasses"
     [info] * matmul.ProcElemDriver
     [info] * matmul.SMatMulDriver

     $ sbt "runMain matmul.ProcElemDriver"
	 $ ls *.v
	 ProcElem.v


Component RTL views
------------------

<figure>
	<figcaption>Processing Element</figcaption>
	<img src="https://raw.githubusercontent.com/kazutomo/Chisel-MatMul/master/figs/PE.png"  width="512" />
</figure>

<figure>
	<figcaption>3x3 matrix multiply design</figcaption>
	<img src="https://raw.githubusercontent.com/kazutomo/Chisel-MatMul/master/figs/MatMul-3x3.png"  width="512" />
</figure>

<figure>
	<figcaption>16x16 matrix multiply design</figcaption>
	<img src="https://raw.githubusercontent.com/kazutomo/Chisel-MatMul/master/figs/MatMul-16x16.png"  width="512" />
</figure>


Waveform views
------------------
<figure>
	<figcaption>3x3 matrix multiply waveform (gtkwave)</figcaption>
	<img src="https://raw.githubusercontent.com/kazutomo/Chisel-MatMul/master/figs/gtkwave-3x3.png"  width="512" />
</figure>



Resource usages example
-----------------------

I compiled the generated 8-bit integer, 3x3 design using Quartus,
targetting a Cyclone V chip (5CGXFC7C7F23C8) as a reference.

	   * Auto DSP balancing
	   ALMs   121/56480 (< 1%)
	   Regs   192
	   BRAM   0
	   DSP    9 / 156 (6%)


	   * DSP off
	   ALMs   348/56480 (< 1%)
	   Regs   240
	   BRAM   0
	   DSP    0 / 156 (6%)

----
Developed by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
