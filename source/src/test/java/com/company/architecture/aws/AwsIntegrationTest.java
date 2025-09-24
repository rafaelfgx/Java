package com.company.architecture.aws;

import com.company.architecture.SpringBootTestConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.ByteArrayInputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Import(SpringBootTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AwsIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnCreatedWhenSendingMessage() throws Exception {
        mockMvc
            .perform(post("/aws/queues/send").contentType(APPLICATION_JSON).content("Message"))
            .andExpectAll(status().isCreated());
    }

    @Test
    void shouldReturnOkWhenUploadingAndDownloadingFile() throws Exception {
        final var file = new MockMultipartFile("file", "Test.txt", "text/plain", new ByteArrayInputStream("Test".getBytes()));

        mockMvc
            .perform(multipart("/aws/files/upload").file(file))
            .andExpect(status().isCreated());

        mockMvc
            .perform(get("/aws/files/download/Test.txt"))
            .andExpectAll(status().isOk());
    }
}
