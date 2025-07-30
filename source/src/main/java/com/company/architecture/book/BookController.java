package com.company.architecture.book;

import com.company.architecture.book.create.CreateBookRequest;
import com.company.architecture.shared.mediator.Mediator;
import com.company.architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final Mediator mediator;

    @Operation(summary = "Create")
    @PostApiResponses
    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody @Valid final CreateBookRequest request) {
        return mediator.handle(request, UUID.class);
    }
}
