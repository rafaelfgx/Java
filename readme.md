# JAVA

![](https://github.com/rafaelfgx/Java/actions/workflows/build.yaml/badge.svg)

Java, Spring Boot, PostgreSQL, MongoDB, Kafka, SQS, S3, Docker, Testcontainers, LocalStack, JWT, Swagger, Mediator Pattern, Outbox Pattern, Strategy Pattern.

## TECHNOLOGIES

* [Java](https://dev.java)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Docker](https://www.docker.com/get-started)
* [Testcontainers](https://testcontainers.com)
* [PostgreSQL](https://www.postgresql.org/)
* [MongoDB](https://www.mongodb.com/docs/manual)
* [Kafka](https://kafka.apache.org)
* [LocalStack](https://localstack.cloud)
* [AWS SQS](https://aws.amazon.com/sqs)
* [AWS S3](https://aws.amazon.com/s3)
* [JWT](https://jwt.io)
* [Swagger](https://swagger.io)

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

* **Api:** Feign [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/api) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/api)
* **AWS:** Amazon Web Services [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/aws) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/aws)
* **Auth:** Authentication and Authorization [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/auth) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/auth)
* **Book:** Mediator Pattern [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/book) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/book)
* **Category:** Cache [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/category) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/category)
* **Game:** Mocks [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/game) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/game)
* **Group:** Groups [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/group) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/group)
* **Invoice:** PostgreSQL [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/invoice) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/invoice)
* **Location:** Flat Object to Nested Object [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/location) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/location)
* **Movie:** Outbox Pattern [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/movie) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/movie)
* **Notification:** Kafka [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/notification) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/notification)
* **Payment:** Strategy Pattern [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/payment) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/payment)
* **Product:** MongoDB [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/product) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/product)
* **User:** Business Rules [Main](https://github.com/rafaelfgx/Java/tree/main/source/src/main/java/com/company/architecture/user) | [Tests](https://github.com/rafaelfgx/Java/tree/main/source/src/test/java/com/company/architecture/user)
