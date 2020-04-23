# Makefile for the Matrix Multiply Chisel project
# written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>

PROJ=matmul
T=mm

all:
	@echo ""
	@echo "$ make test       # run Scala test"
	@echo "$ make simulate   # invoke Verilator"
	@echo "$ make verilog    # only generate Verilog codes"
	@echo ""
	@echo "Shorter form for convinice"
	@echo "$ make t"
	@echo "$ make s"
	@echo "$ make v"
	@echo ""
#	@echo "To test invididual module"
#	@echo "$ make test T=$target"
#	@echo ""
#	@echo ""


v verilog:
	sbt "test:runMain ${PROJ}.TestMain $T:verilog"

t test:
	sbt "test:runMain ${PROJ}.TestMain $T"

s simulate:
	sbt "test:runMain ${PROJ}.TestMain $T --backend-name verilator"

#l list:

h help:
	sbt "test:runMain $PROJ.TestMain --help"

clean:
	rm -rf project target test_run_dir generated *.class
	rm -rf *.fir *.anno.json
