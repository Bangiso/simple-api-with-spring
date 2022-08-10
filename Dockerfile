FROM openjdk:8
ADD target/simple-api-with-spring.jar  simple-api-with-spring.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "simple-api-with-spring.jar"]