FROM java:8 

ADD target/*.jar app.jar

RUN bash -c 'touch /app.war'

EXPOSE 4567

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]

#CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/sparkexample-jar-with-dependencies.jar"]