package com.company.architecture.location;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class State extends Location {
    private List<City> cities = new ArrayList<>();
}
