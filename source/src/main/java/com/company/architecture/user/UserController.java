package com.company.architecture.user;

import com.company.architecture.shared.swagger.DefaultApiResponses;
import com.company.architecture.shared.swagger.GetApiResponses;
import com.company.architecture.shared.swagger.PostApiResponses;
import com.company.architecture.user.dtos.AddUserDto;
import com.company.architecture.user.dtos.GetUserDto;
import com.company.architecture.user.dtos.UpdateUserDto;
import com.company.architecture.user.dtos.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get")
    @GetApiResponses
    @GetMapping
    public Page<UserDto> get(@ParameterObject @ModelAttribute @Valid final GetUserDto dto) {
        return userService.get(dto);
    }

    @Operation(summary = "Get")
    @GetApiResponses
    @GetMapping("{id}")
    public UserDto get(@PathVariable final UUID id) {
        return userService.get(id);
    }

    @Operation(summary = "Add")
    @PostApiResponses
    @PostMapping
    public UUID add(@RequestBody @Valid final AddUserDto dto) {
        return userService.add(dto);
    }

    @Operation(summary = "Update")
    @DefaultApiResponses
    @PutMapping("{id}")
    public void update(@PathVariable final UUID id, @RequestBody @Valid final UpdateUserDto dto) {
        userService.update(dto.withId(id));
    }

    @Operation(summary = "Delete")
    @DefaultApiResponses
    @DeleteMapping("{id}")
    public void delete(@PathVariable final UUID id) {
        userService.delete(id);
    }
}
