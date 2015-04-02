GS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        NileServer.java\
        OrderServer.java \
        CatalogServer.java \
        Client.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
