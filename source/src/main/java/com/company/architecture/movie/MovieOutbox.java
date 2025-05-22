package com.company.architecture.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table
public class MovieOutbox {
    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    @OneToOne
    private Movie movie;

    public MovieOutbox(Movie movie) {
        this.movie = movie;
    }
}
