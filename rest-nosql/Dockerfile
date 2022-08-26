FROM openjdk:11
ADD target/movies-producing-mongo.jar movies-producing-mongo.jar
ADD target/classes/data.tsv data.tsv
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=container", "-jar", "movies-producing-mongo.jar"]