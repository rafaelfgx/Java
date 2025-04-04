package com.company.architecture.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class MovieOutbox {
    @Id
    private UUID id;

    @NotNull
    private String movie;
}
