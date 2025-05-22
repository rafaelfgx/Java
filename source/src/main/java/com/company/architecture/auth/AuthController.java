package com.company.architecture.auth;

import com.company.architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Auth")
    @PostApiResponses
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody @Valid final Auth auth) {
        return authService.auth(auth);
    }
}
