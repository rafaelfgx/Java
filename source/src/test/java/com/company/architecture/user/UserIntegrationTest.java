package com.company.architecture.user;

import com.company.architecture.SpringBootTestConfiguration;
import com.company.architecture.shared.Data;
import com.company.architecture.shared.services.MapperService;
import com.company.architecture.user.dtos.AddUserDto;
import com.company.architecture.user.dtos.UpdateUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Import(SpringBootTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MapperService mapperService;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @BeforeEach
    void beforeEach() {
        mongoTemplate.getDb().drop();
    }

    @ParameterizedTest
    @ValueSource(strings = {"?name=inexistent", "/" + Data.ID_INEXISTENT})
    void shouldReturnNotFoundWhenGettingUsers(String uri) throws Exception {
        mockMvc.perform(get("/users" + uri)).andExpectAll(status().isNotFound());
    }

    @ParameterizedTest
    @ValueSource(strings = {"page=0", "size=1", "sort=id", "sort=name", "direction=ASC", "direction=DESC", "name=User"})
    void shouldReturnOkWhenGettingUsers(String uri) throws Exception {
        mongoTemplate.save(Data.USER);

        mockMvc.perform(get("/users?" + uri)).andExpectAll(
            status().isOk(),
            jsonPath("$.content").isArray(),
            jsonPath("$.content.length()").value(1),
            jsonPath("$.content[0].id").value(Data.USER.getId().toString()),
            jsonPath("$.content[0].name").value(Data.USER.getName()),
            jsonPath("$.content[0].email").value(Data.USER.getEmail()),
            jsonPath("$.page.number").value(0),
            jsonPath("$.page.totalElements").value(1),
            jsonPath("$.page.totalPages").value(1)
        );
    }

    @Test
    void shouldReturnOkWhenGettingUserById() throws Exception {
        mongoTemplate.save(Data.USER);

        mockMvc.perform(get("/users/" + Data.USER.getId())).andExpectAll(
            status().isOk(),
            jsonPath("$.id").value(Data.USER.getId().toString()),
            jsonPath("$.name").value(Data.USER.getName()),
            jsonPath("$.email").value(Data.USER.getEmail())
        );
    }

    @Test
    void shouldReturnConflictWhenCreatingUser() throws Exception {
        mongoTemplate.save(Data.USER);

        mockMvc
            .perform(post("/users").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(mapperService.map(Data.USER, AddUserDto.class))))
            .andExpectAll(status().isConflict());
    }

    @Test
    void shouldReturnCreatedWhenCreatingUser() throws Exception {
        mockMvc
            .perform(post("/users").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(mapperService.map(Data.USER, AddUserDto.class))))
            .andExpectAll(status().isCreated());
    }

    @Test
    void shouldReturnConflictWhenUpdatingUser() throws Exception {
        mongoTemplate.save(Data.USER);
        mongoTemplate.save(Data.USER_CONFLICT);

        mockMvc
            .perform(put("/users/" + Data.USER.getId()).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(mapperService.map(Data.USER_CONFLICT, UpdateUserDto.class))))
            .andExpectAll(status().isConflict());
    }

    @Test
    void shouldReturnOkWhenUpdatingUser() throws Exception {
        mongoTemplate.save(Data.USER);

        mockMvc
            .perform(put("/users/" + Data.USER.getId()).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(mapperService.map(Data.USER_UPDATE, UpdateUserDto.class))))
            .andExpectAll(status().isOk());

        final var user = mongoTemplate.findById(Data.USER.getId(), User.class);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(Data.USER_UPDATE.getName(), user.getName());
        Assertions.assertEquals(Data.USER_UPDATE.getEmail(), user.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {Data.ID, Data.ID_INEXISTENT})
    void shouldReturnOkWhenDeletingUser(String id) throws Exception {
        mongoTemplate.save(Data.USER);

        mockMvc
            .perform(delete("/users/" + id))
            .andExpectAll(status().isOk());

        Assertions.assertNull(mongoTemplate.findById(id, User.class));
    }
}
