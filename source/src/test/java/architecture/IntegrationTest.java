package architecture;

import architecture.auth.Authority;
import architecture.auth.JwtService;
import architecture.shared.services.MapperService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class IntegrationTest {
    @ServiceConnection
    static final PostgreSQLContainer<?> postgre = PostgreTest.postgre;

    @ServiceConnection
    static final MongoDBContainer mongo = MongoTest.mongo;

    @Container
    static final LocalStackContainer localstack = LocalStackTest.localstack;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @Autowired
    protected MapperService mapperService;

    @DynamicPropertySource
    static void overrideConfiguration(DynamicPropertyRegistry registry) {
        LocalStackTest.registry(localstack, registry);
    }

    @BeforeAll
    static void beforeAll() {
        PostgreTest.beforeAll();
        MongoTest.beforeAll();
        LocalStackTest.beforeAll();
    }

    @BeforeEach
    void beforeEach() {
        Mockito.when(jwtService.verify("")).thenReturn(true);
        Mockito.when(jwtService.getSubject("")).thenReturn(UUID.randomUUID().toString());
        Mockito.when(jwtService.getAuthorities("")).thenReturn(AuthorityUtils.createAuthorityList(Authority.ADMINISTRATOR.toString()));
        mongoTemplate.getDb().drop();
    }

    protected ResultActions get(String uri) throws Exception {
        return perform(HttpMethod.GET, uri, null);
    }

    protected ResultActions post(String uri, Object body) throws Exception {
        return perform(HttpMethod.POST, uri, body);
    }

    protected ResultActions put(String uri, Object body) throws Exception {
        return perform(HttpMethod.PUT, uri, body);
    }

    protected ResultActions patch(String uri) throws Exception {
        return perform(HttpMethod.PATCH, uri, null);
    }

    protected ResultActions delete(String uri) throws Exception {
        return perform(HttpMethod.DELETE, uri, null);
    }

    protected ResultActions multipart(String uri, MockMultipartFile file) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.multipart(uri).file(file));
    }

    ResultActions perform(HttpMethod method, String uri, Object body) throws Exception {
        final var builder = MockMvcRequestBuilders.request(method, uri).contentType(MediaType.APPLICATION_JSON).content(mapperService.json(body));
        return mockMvc.perform(builder).andDo(result -> System.out.printf(" Uri: %s%n Method: %s%n Request: %s%n Response: %s%n%n", uri, method, body, result.getResponse().getContentAsString()));
    }
}
