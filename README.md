# movies api

This api was made to practice development skills in spring boot and java.
It´s a simple api to provide some features about movies.

#Usage

this app was maded on maven structure, so to run in the first time you need be installed mvn and run:

mvn clean install

#Runing
To run this aplication you can use:
mvn spring-boot:run
or
mvn package
java -jar <arctifact-name.jar>

#Api routes
Detailed routes and examples can be checked in http://localhost:8080/swagger-ui.html
 
#Tecnolies
Spring data rest
h2
swagger

#Profiles
project has a dev profile. To activate set profile parameter configure spring.profiles.active=dev in your setup

#Deploy
this project was deployed in https://movies-api-2.herokuapp.com/



