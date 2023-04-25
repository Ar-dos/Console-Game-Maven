all: build

build:
	mvn package
	java -jar target/Game-1.0-jar-with-dependencies.jar --enemiesCount=4 --wallsCount=4 --size=10 --profile=production

start:
	java -jar target/Game-1.0-jar-with-dependencies.jar --enemiesCount=4 --wallsCount=4 --size=10 --profile=development

clean:
	rm -rf target/*