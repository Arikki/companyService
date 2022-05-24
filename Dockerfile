FROM public.ecr.aws/b4j4y7d9/openjdk:8
EXPOSE 8081
ADD /target/companyService.jar companyService.jar
ENTRYPOINT ["java","-jar","/companyService.jar"]