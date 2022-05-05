FROM openjdk:8
EXPOSE 8081
ADD /target/companyService.jar companyService.jar
ENTRYPOINT ["java","-jar","/companyService.jar"]