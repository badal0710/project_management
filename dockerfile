from maven as build
WORKDIR /app
COPY . .
RUN mvn install

FROM openjdk:17.0
WORKDIR /app
COPY --from=build /app/target/ProjectManagementBackend.jar /app/
EXPOSE 9090
CMD [ "java","-jar","ProjectManagementBackend.jar" ]