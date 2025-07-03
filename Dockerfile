# Using an old, vulnerable base image for demonstration purposes
FROM openjdk:8u151-jre-alpine

# The application's JAR file
ARG JAR_FILE=target/*.jar

# Add the application's JAR to the container
COPY ${JAR_FILE} app.jar

# Run the JAR file 
ENTRYPOINT ["java","-jar","/app.jar"]
