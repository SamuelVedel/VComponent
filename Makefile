SRC_DIR := src
OUT_DIR := bin
OUT_DIR_TST := bin_tst

SRCS := $(wildcard $(SRC_DIR)/fr/svedel/vcomponent/*.java)
SRCS_TST := $(wildcard $(SRC_DIR)/tst/vcomponent/*.java)
#CLS := $(SRCS:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)

JC := javac
JCFLAGS := -encoding iso-8859-1 -d $(OUT_DIR)/ -cp $(SRC_DIR)/
JCFLAGS_TST := -encoding iso-8859-1 -d $(OUT_DIR_TST)/ -cp $(SRC_DIR)/

JAR_FILE = vcomponent.jar
INSTALL_PATH ?= $(HOME)/javalibs/vcomponent

.SUFFIXES: .java .class

.PHONY: all clean build build_tst run_tst jar

all: build build_tst run_tst

build: .done

build_tst: .done_tst

install: .done
	cp -r $(OUT_DIR)/* $(INSTALL_PATH)

run_tst:
	java -cp $(OUT_DIR_TST) tst.vcomponent.MainTestVc

jar: $(JAR_FILE)

#%.class: %.java
#	$(JC) $(JCFLAGS) $?

.done: $(SRCS)
	$(JC) $(JCFLAGS) $?
	touch .done

.done_tst: $(SRCS_TST)
	$(JC) $(JCFLAGS_TST) $?
	touch .done_tst

$(JAR_FILE):
	jar -cf $@ -C $(OUT_DIR) .

clean:
	rm -rf $(OUT_DIR) $(OUT_DIR_TST)
	rm -f .done .done_tst
	rm -f *~ $(SRC_DIR)/*/*/*/*~ $(SRC_DIR)/*/*/*/*/*~ $(SRC_DIR)/*/*/*/*/*/*~
