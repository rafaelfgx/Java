package com.company.architecture;

import com.company.architecture.auth.Authority;
import com.company.architecture.auth.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

public abstract class ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    JwtService jwtService;

    @BeforeEach
    void beforeEach() {
        when(jwtService.getSubject(anyString())).thenReturn(UUID.randomUUID().toString());
        when(jwtService.getAuthorities(anyString())).thenReturn(createAuthorityList(Authority.ADMINISTRATOR.toString()));
        when(jwtService.create(any())).thenReturn("");
        when(jwtService.verify(anyString())).thenReturn(true);
    }

    protected ResultActions perform(HttpMethod method, String uri) throws Exception {
        final var builder = MockMvcRequestBuilders.request(method, uri).contentType(MediaType.APPLICATION_JSON);
        return mockMvc.perform(builder).andDo(result -> System.out.printf(" Uri: %s%n Method: %s%n Response: %s%n%n", uri, method, result.getResponse().getContentAsString()));
    }

    protected ResultActions perform(HttpMethod method, String uri, Object body) throws Exception {
        final var builder = MockMvcRequestBuilders.request(method, uri).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(body));
        return mockMvc.perform(builder).andDo(result -> System.out.printf(" Uri: %s%n Method: %s%n Request: %s%n Response: %s%n%n", uri, method, body, result.getResponse().getContentAsString()));
    }

    protected ResultActions multipart(String uri, MockMultipartFile file) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.multipart(uri).file(file));
    }
}
