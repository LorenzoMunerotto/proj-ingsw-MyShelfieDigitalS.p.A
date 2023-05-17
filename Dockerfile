
FROM maven:latest AS build  
COPY src src
COPY pom.xml .
RUN mvn -e clean package

EXPOSE 1235 
ENTRYPOINT ["java","-jar","target/proj-ingsw-MyShelfieDigitalS.P.A.-1.0-SNAPSHOT-jar-with-dependencies.jar"]


