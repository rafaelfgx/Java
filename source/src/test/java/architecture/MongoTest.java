package architecture;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.containers.MongoDBContainer;

@DataMongoTest
public abstract class MongoTest {
    @ServiceConnection
    static final MongoDBContainer mongo = new MongoDBContainer("mongo");

    @Autowired
    protected MongoTemplate mongoTemplate;

    @BeforeAll
    static void beforeAll() {
        mongo.start();
    }

    @BeforeEach
    void beforeEach() {
        mongoTemplate.getDb().drop();
    }
}