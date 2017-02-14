JAVA = "java"
JAVAC = "javac"
OPENJMLJAR = "..\..\OpenJML\openjml.jar"
JMLC = $(JAVA) -jar $(OPENJMLJAR)  -rac -nopuritycheck -racCheckAssumptions -racPreconditionEntry -d ..\bin
JML = $(JAVA) -jar $(OPENJMLJAR) -nopuritycheck 
RM = del
JUNIT4 = "C:\Program Files (x86)\JUnit\junit4.12\junit-4.12.jar"
HAMCREST = "C:\Program Files (x86)\JUnit\junit4.12\hamcrest-core-1.3.jar"
JMLRUNTIME = "..\..\OpenJML\jmlruntime.jar"
JMLCLASSPATH = .;$(JMLRUNTIME)
JMLJUNITCLASSPATH = $(JMLCLASSPATH);$(JUNIT4);$(HAMCREST)

..\bin\Explosives.class : Explosives.java
	$(JMLC) Explosives.java

..\bin\TS_TestAssign001.class : ..\bin\Explosives.class TS_TestAssign001.java
	$(JAVAC) -cp ..\bin;$(JMLJUNITCLASSPATH) -d ..\bin  TS_TestAssign001.java

TS_TestAssign001.run : ..\bin\TS_TestAssign001.class
	 $(JAVA) -cp ..\bin;$(JMLJUNITCLASSPATH) TS_TestAssign001

clean : 
	$(RM) ..\\bin\\*.class
