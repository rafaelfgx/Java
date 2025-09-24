package com.company.architecture.location;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Country extends Location {
    private List<State> states = new ArrayList<>();
}
