# Makefile for the Matrix Multiply Chisel project
# written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>

all:
	@echo ""
	@echo "$ make list       # lists test benches and drivers"
	@echo "$ make test       # runs all Chisel tests"
	@echo "$ make verilog    # generates Verilog code"
#	@echo "$ make simulate   # invokes Verilator"
	@echo ""

list:
	@echo "List test benches"
	@sbt "show Test/definedTestNames"
	@echo
	@echo "List drivers that generates Verilog file"
	@sbt "show Compile/discoveredMainClasses"
	@echo

t test:
	@sbt "testOnly matmul.* -- -l RequiresVerilator"

s simulate:
	@sbt "testOnly matmul.* -- -l RequiresTreadle" # run only Verilator

v verilog:
	@sbt "runMain matmul.SMatMulDriver"

#s simulate:
#	sbt "test:runMain ${PROJ}.TestMain $T --backend-name verilator"
#v verilog:
#	SBT_OPTS="-Xms512M -Xmx2048M -Xss8M -XX:MaxMetaspaceSize=1024M" sbt "test:runMain ${PROJ}.TestMain $T:verilog"


clean:
	rm -rf project target test_run_dir generated
	rm -f *.fir *.anno.json *.class

distclean: clean
	rm -f *.v
