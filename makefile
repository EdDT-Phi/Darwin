default:
	javac *.java
run: default
	java Main
test: default
	java Main --debug
clean:
	rm -f *.class