# JAVA

![](https://github.com/rafaelfgx/Java/actions/workflows/build.yaml/badge.svg)

Java, Spring Boot, Docker, Testcontainers, PostgreSQL, MongoDB, Kafka, LocalStack, AWS (SQS, S3), JWT, Swagger, Patterns (Mediator, Observer, Outbox, Strategy).

## TECHNOLOGIES

- [Java](https://dev.java)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com/get-started)
- [Testcontainers](https://java.testcontainers.org)
- [PostgreSQL](https://www.postgresql.org/)
- [MongoDB](https://www.mongodb.com/docs/manual)
- [Kafka](https://kafka.apache.org)
- [LocalStack](https://localstack.cloud)
- [AWS SQS](https://aws.amazon.com/sqs)
- [AWS S3](https://aws.amazon.com/s3)
- [JWT](https://jwt.io)
- [Swagger](https://swagger.io)

## RUN

<details>
<summary>IntelliJ IDEA</summary>

#### Prerequisites

* [Docker](https://www.docker.com/get-started)
* [Java JDK](https://www.oracle.com/java/technologies/downloads)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download)

#### Steps

1. Execute **docker compose up --detach --build --force-recreate --remove-orphans** in **docker** directory.
2. Open **source** directory in **IntelliJ IDEA**.
3. Select **Application.java** class.
4. Click **Run** or **Debug**.
5. Open <http://localhost:8080>.

</details>

<details>
<summary>Docker</summary>

#### Prerequisites

* [Docker](https://www.docker.com/get-started)

#### Steps

1. Execute **docker compose up --detach --build --force-recreate --remove-orphans** in **docker** directory.
2. Open <http://localhost:8090>.

</details>

## EXAMPLES

- **Authentication and Authorization:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/auth) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/auth)
- **Cache:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/category) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/category)
- **Feign:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/api) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/api)
- **Kafka:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/notification) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/notification)
- **Mocks:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/game) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/game)
- **Amazon Web Services:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/aws) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/aws)
- **Databases - MongoDB:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/product) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/product)
- **Databases - PostgreSQL:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/invoice) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/invoice)
- **Patterns - Mediator:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/book) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/book)
- **Patterns - Observer:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/car) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/car)
- **Patterns - Outbox:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/movie) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/movie)
- **Patterns - Strategy:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/payment) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/payment)
- **Patterns - UseCase:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/animal) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/animal)
- **Logic - Business Rules:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/user) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/user)
- **Logic - Flat Object to Nested Object:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/location) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/location)
- **Logic - Groups:** [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/group) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/group)
