Chisel matrix multiply systolic implementation

not completed yet. 


Getting Started
---------------

     $ git clone https://github.com/kazutomo/Chisel-MatMul.git
     $ cd Chisel-MatMul
     $ make test

NOTE: tested on Fedora 28 with sbt 1.3.7

How to use HACOGen
--------------

     $ make test       # runs Scala test.
     $ make simulate   # invokes Verilator and generates vcd.
     $ make verilog    # generates Verilog codes.

Shorter target names for convinice

     $ make t
     $ make s
     $ make v

To test invididual module:

     $ make test T=pe

To list available target:

     $ make list
     Available targets: pe




----
Developed by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
