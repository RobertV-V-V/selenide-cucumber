FROM markhobson/maven-chrome

COPY ./pom.xml ./pom.xml

RUN mvn -B dependency:resolve dependency:resolve-plugins

COPY ./src ./src

RUN chmod +x ./src &&\
    mvn package -DskipTests &&\
    mvn allure:install