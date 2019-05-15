# Jessica Arrouasse
# ID: 328786348 
# username: anidjaj

compile: bin
	 javac -cp biuoop-1.4.jar:src -d bin src/*.java
	
jar: compile
	 jar cfm ass5game.jar Manifest.mf -C bin .

run:
	java -cp ass5game.jar Ass5Game
	
bin:
	mkdir bin

	
