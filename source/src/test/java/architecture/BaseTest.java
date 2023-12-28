package architecture;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.localstack.LocalStackContainer.Service;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class BaseTest {
    @ServiceConnection
    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres");

    @ServiceConnection
    static final MongoDBContainer mongo = new MongoDBContainer("mongo");

    @Container
    static final LocalStackContainer localstack = new LocalStackContainer(DockerImageName.parse("localstack/localstack")).withServices(Service.SQS, LocalStackContainer.EnabledService.named("sqs-query"), Service.S3);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @DynamicPropertySource
    static void overrideConfiguration(DynamicPropertyRegistry registry) {
        registry.add("spring.cloud.aws.region.static", localstack::getRegion);
        registry.add("spring.cloud.aws.credentials.access-key", localstack::getAccessKey);
        registry.add("spring.cloud.aws.credentials.secret-key", localstack::getSecretKey);
        registry.add("spring.cloud.aws.endpoint", localstack::getEndpoint);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
        mongo.start();
        localstack.start();
    }

    @BeforeEach
    void beforeEach() {
        mongoTemplate.getDb().drop();
    }

    ResultActions perform(HttpMethod method, String uri, Object body) throws Exception {
        final var builder = MockMvcRequestBuilders.request(method, uri).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(body));
        return mockMvc.perform(builder).andDo(result -> System.out.printf(" Uri: %s%n Method: %s%n Request: %s%n Response: %s%n%n", uri, method, body, result.getResponse().getContentAsString()));
    }

    public ResultActions get(String uri) throws Exception {
        return perform(HttpMethod.GET, uri, null);
    }

    public ResultActions post(String uri, Object body) throws Exception {
        return perform(HttpMethod.POST, uri, body);
    }

    public ResultActions put(String uri, Object body) throws Exception {
        return perform(HttpMethod.PUT, uri, body);
    }

    public ResultActions patch(String uri) throws Exception {
        return perform(HttpMethod.PATCH, uri, null);
    }

    public ResultActions delete(String uri) throws Exception {
        return perform(HttpMethod.DELETE, uri, null);
    }

    public ResultActions multipart(String uri, MockMultipartFile file) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.multipart(uri).file(file));
    }
}
