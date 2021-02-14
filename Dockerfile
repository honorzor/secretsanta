FROM openjdk:11
ARG VERSION
ARG PROFILE
ENV PROFILE=${PROFILE}
COPY backend/build/libs/backend-${VERSION}.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=${PROFILE}"]
