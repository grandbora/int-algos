run: compile
	java -cp .:./lib/* Solution

compile: clean
	javac -cp .:./lib/* Solution.java

clean:
	rm -f Solution*.class

.PHONY: run compile clean