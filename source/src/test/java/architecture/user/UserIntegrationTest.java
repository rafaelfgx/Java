package architecture.user;

import architecture.IntegrationTest;
import architecture.Data;
import architecture.user.dtos.AddUserDto;
import architecture.user.dtos.UpdateUserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserIntegrationTest extends IntegrationTest {
    @ParameterizedTest
    @ValueSource(strings = { "?page=-1", "?page=9999999999", "?page=X", "?size=0", "?size=9999999999", "?size=X", "?direction=X", "/1", "/X" })
    void testGetShouldReturnBadRequest(String uri) throws Exception {
        get("/users" + uri).andExpectAll(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = { "?name=inexistent", "/" + Data.idInexistent })
    void testGetShouldReturnNotFound(String uri) throws Exception {
        get("/users" + uri).andExpectAll(status().isNotFound());
    }

    @ParameterizedTest
    @ValueSource(strings = { "page=0", "size=1", "sort=id", "sort=name", "direction=ASC", "direction=DESC", "name=User" })
    void testGetShouldReturnOk(String uri) throws Exception {
        mongoTemplate.save(Data.user);
        get("/users?" + uri).andExpectAll(
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
            jsonPath("$.content[0].id").value(Data.user.getId().toString()),
            jsonPath("$.content[0].name").value(Data.user.getName()),
            jsonPath("$.content[0].email").value(Data.user.getEmail())
        );
    }

    @Test
    void testGetShouldReturnOk() throws Exception {
        mongoTemplate.save(Data.user);
        get("/users/" + Data.user.getId()).andExpectAll(
            status().isOk(),
            jsonPath("$.id").value(Data.user.getId().toString()),
            jsonPath("$.name").value(Data.user.getName()),
            jsonPath("$.email").value(Data.user.getEmail())
        );
    }

    @Test
    void testPostShouldReturnConflict() throws Exception {
        mongoTemplate.save(Data.user);
        post("/users", mapperService.map(Data.user, AddUserDto.class)).andExpectAll(status().isConflict());
    }

    @Test
    void testPostShouldReturnCreated() throws Exception {
        post("/users", mapperService.map(Data.user, AddUserDto.class)).andExpectAll(status().isCreated());
    }

    @Test
    void testPutShouldReturnConflict() throws Exception {
        mongoTemplate.save(Data.user);
        mongoTemplate.save(Data.userConflict);
        put("/users/" + Data.user.getId(), mapperService.map(Data.userConflict, UpdateUserDto.class)).andExpectAll(status().isConflict());
    }

    @Test
    void testPutShouldReturnOk() throws Exception {
        mongoTemplate.save(Data.user);
        put("/users/" + Data.user.getId(), mapperService.map(Data.userUpdate, UpdateUserDto.class)).andExpectAll(status().isOk());
        final var user = mongoTemplate.findById(Data.user.getId(), User.class);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(Data.userUpdate.getName(), user.getName());
        Assertions.assertEquals(Data.userUpdate.getEmail(), user.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = { Data.id, Data.idInexistent })
    void testDeleteShouldReturnOk(String id) throws Exception {
        mongoTemplate.save(Data.user);
        delete("/users/" + id).andExpectAll(status().isOk());
        Assertions.assertNull(mongoTemplate.findById(id, User.class));
    }
}
