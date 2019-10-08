# Carte aux trésors

## Build
```sh
mvn clean install
```

It will generate a "target/carte-aux-tresors-*-jar-with-dependencies.jar" file
that is runnable with below command

## Run

Application is expecting 2 parameters to be passed:

```sh
parameter 1: input file with the map description, must exists
parameter 2: input file with the map description, will be created and should not exist before staringthe app

java com.vaubrun.App path/to/inputFile path/for/outputFile 
java -jar carte-aux-tresors-*-jar-with-dependencies.jar path/to/inputFile path/for/outputFile 
```

## Notes

JUnit 5 support seems to be still young. To understand set up please see 
[this](https://www.mkyong.com/junit5/junit-5-maven-examples/) & [this](https://stackoverflow.com/a/17671104) 