package com.company.architecture.user;

import com.company.architecture.Data;
import com.company.architecture.IntegrationTest;
import com.company.architecture.user.dtos.AddUserDto;
import com.company.architecture.user.dtos.UpdateUserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserIntegrationTest extends IntegrationTest {
    @ParameterizedTest
    @ValueSource(strings = {"?name=inexistent", "/" + Data.ID_INEXISTENT})
    void testGetShouldReturnNotFound(String uri) throws Exception {
        perform(GET, "/users" + uri).andExpectAll(status().isNotFound());
    }

    @ParameterizedTest
    @ValueSource(strings = {"page=0", "size=1", "sort=id", "sort=name", "direction=ASC", "direction=DESC", "name=User"})
    void testGetShouldReturnOk(String uri) throws Exception {
        mongoTemplate.save(Data.USER);
        perform(GET, "/users?" + uri).andExpectAll(
            status().isOk(),
            jsonPath("$.content").isArray(),
            jsonPath("$.content.length()").value(1),
            jsonPath("$.empty").value(false),
            jsonPath("$.first").value(true),
            jsonPath("$.last").value(true),
            jsonPath("$.numberOfElements").value(1),
            jsonPath("$.totalElements").value(1),
            jsonPath("$.number").value(0),
            jsonPath("$.totalPages").value(1),
            jsonPath("$.sort.empty").value(false),
            jsonPath("$.sort.sorted").value(true),
            jsonPath("$.sort.unsorted").value(false),
            jsonPath("$.content[0].id").value(Data.USER.getId().toString()),
            jsonPath("$.content[0].name").value(Data.USER.getName()),
            jsonPath("$.content[0].email").value(Data.USER.getEmail())
        );
    }

    @Test
    void testGetShouldReturnOk() throws Exception {
        mongoTemplate.save(Data.USER);
        perform(GET, "/users/" + Data.USER.getId()).andExpectAll(
            status().isOk(),
            jsonPath("$.id").value(Data.USER.getId().toString()),
            jsonPath("$.name").value(Data.USER.getName()),
            jsonPath("$.email").value(Data.USER.getEmail())
        );
    }

    @Test
    void testPostShouldReturnConflict() throws Exception {
        mongoTemplate.save(Data.USER);
        perform(POST, "/users", mapperService.map(Data.USER, AddUserDto.class)).andExpectAll(status().isConflict());
    }

    @Test
    void testPostShouldReturnCreated() throws Exception {
        perform(POST, "/users", mapperService.map(Data.USER, AddUserDto.class)).andExpectAll(status().isCreated());
    }

    @Test
    void testPutShouldReturnConflict() throws Exception {
        mongoTemplate.save(Data.USER);
        mongoTemplate.save(Data.USER_CONFLICT);
        perform(PUT, "/users/" + Data.USER.getId(), mapperService.map(Data.USER_CONFLICT, UpdateUserDto.class)).andExpectAll(status().isConflict());
    }

    @Test
    void testPutShouldReturnOk() throws Exception {
        mongoTemplate.save(Data.USER);
        perform(PUT, "/users/" + Data.USER.getId(), mapperService.map(Data.USER_UPDATE, UpdateUserDto.class)).andExpectAll(status().isOk());
        final var user = mongoTemplate.findById(Data.USER.getId(), User.class);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(Data.USER_UPDATE.getName(), user.getName());
        Assertions.assertEquals(Data.USER_UPDATE.getEmail(), user.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {Data.ID, Data.ID_INEXISTENT})
    void testDeleteShouldReturnOk(String id) throws Exception {
        mongoTemplate.save(Data.USER);
        perform(DELETE, "/users/" + id).andExpectAll(status().isOk());
        Assertions.assertNull(mongoTemplate.findById(id, User.class));
    }
}
