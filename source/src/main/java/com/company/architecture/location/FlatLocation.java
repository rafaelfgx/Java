package com.company.architecture.location;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlatLocation {
    private Location country;
    private Location state;
    private Location city;
}
