package com.company.architecture.car;

import com.company.architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cars")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @Operation(summary = "Add")
    @PostApiResponses
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody @Valid final Car car) {
        carService.add(car);
    }
}
