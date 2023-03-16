FROM openjdk:19-oracle
ADD target/school-reyfow-students*.jar app
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "app"]