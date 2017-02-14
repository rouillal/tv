JAVA = "java"
JAVAC = "javac"
OPENJMLJAR = "../../../OpenJML/openjml.jar"
JMLC = $(JAVA) -jar $(OPENJMLJAR)  -rac -nopuritycheck -racCheckAssumptions -racPreconditionEntry -d ../bin
JML = $(JAVA) -jar $(OPENJMLJAR) -nopuritycheck 
RM = rm
JUNIT4 = "../../../junit-4.12.jar"
HAMCREST = "../../../hamcrest-core-1.3.jar"
JMLRUNTIME = "../../../OpenJML/jmlruntime.jar"
JMLCLASSPATH = .:$(JMLRUNTIME)
JMLJUNITCLASSPATH = $(JMLCLASSPATH):$(JUNIT4):$(HAMCREST)

../bin/Explosives.class : Explosives.java
	$(JMLC) Explosives.java

../bin/TS_TestAssign001.class : ../bin/Explosives.class TS_TestAssign001.java
	$(JAVAC) -cp ../bin:$(JMLJUNITCLASSPATH) -d ../bin  TS_TestAssign001.java

TS_TestAssign001.run : ../bin/TS_TestAssign001.class
	 $(JAVA) -cp ../bin:$(JMLJUNITCLASSPATH) TS_TestAssign001

../bin/TS_TestAssign002.class : ../bin/Explosives.class TS_TestAssign002.java
	$(JAVAC) -cp ../bin:$(JMLJUNITCLASSPATH) -d ../bin  TS_TestAssign002.java

TS_TestAssign002.run : ../bin/TS_TestAssign002.class
	 $(JAVA) -cp ../bin:$(JMLJUNITCLASSPATH) TS_TestAssign002

../bin/TS_TestAssign003.class : ../bin/Explosives.class TS_TestAssign003.java
	$(JAVAC) -cp ../bin:$(JMLJUNITCLASSPATH) -d ../bin  TS_TestAssign003.java

TS_TestAssign003.run : ../bin/TS_TestAssign003.class
	 $(JAVA) -cp ../bin:$(JMLJUNITCLASSPATH) TS_TestAssign003

../bin/TS_TestAssign004.class : ../bin/Explosives.class TS_TestAssign004.java
	$(JAVAC) -cp ../bin:$(JMLJUNITCLASSPATH) -d ../bin  TS_TestAssign004.java

TS_TestAssign004.run : ../bin/TS_TestAssign004.class
	 $(JAVA) -cp ../bin:$(JMLJUNITCLASSPATH) TS_TestAssign004

clean : 
	$(RM) ../bin/*.class
