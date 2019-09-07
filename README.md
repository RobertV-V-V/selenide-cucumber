Selenide + Cucumber test suite
========================

This is a test suite for the helsinki.craigslist.org site using Selenide with Cucumber and Maven.

### Running in docker
Please install:
- Docker

#### How to run

```
docker-compose up -d
docker exec -it cucumber_tests mvn test 
```

#### Report

To get report please run:
```
docker exec -it cucumber_tests mvn allure:report
```
You can find generated report on you machine here:
```
target/site/allure-maven-plugin/index.html
```
### Running on a local machine
Please install:
- Java
- Maven

#### How to run

To run tests type from command line:

```
mvn clean test
```
#### Report

To get report please run:
```
mvn allure:serve
```