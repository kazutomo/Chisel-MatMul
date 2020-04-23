Chisel matrix multiply systolic implementation

Currently it only supports a square matrix.


Getting Started
---------------

     $ git clone https://github.com/kazutomo/Chisel-MatMul.git
     $ cd Chisel-MatMul
     $ make test

NOTE: tested on Fedora 28 with sbt 1.3.7

Usage: type 'make' without argument
--------------

     $ make test       # compile the design and runs Scala test.
     $ make simulate   # invokes Verilator and generates vcd.
     $ make verilog    # generates Verilog codes.

Shorter target names for convinice

     $ make t
     $ make s
     $ make v


To compile the design and run test:

     $ make test
     sbt "test:runMain matmul.TestMain mm"
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


To test invididual module:

     $ make test T=pe

     Note: w/o T, 'mm' is default target

To list available target:

     $ make list
     Available targets: mm pe

     pe: only processing element
     mm: the entire matrix multiply



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


----
Developed by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
