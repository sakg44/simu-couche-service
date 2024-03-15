#use openjdk 19 image as base image
FROM openjdk:19-ea-1-jdk-oracle
#Create à new directory
RUN mkdir /app
RUN echo sakg va builder l image docker

#Cop the app files from host to image filesystem
COPY target/bankAccountService-1.0-SNAPSHOT.jar /app

#Set the dirtectory for executing future commands
WORKDIR /app

CMD java -cp bankAccountService-1.0-SNAPSHOT.jar org.sakg.Main