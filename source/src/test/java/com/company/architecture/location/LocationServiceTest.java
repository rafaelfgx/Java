package com.company.architecture.location;

import com.company.architecture.shared.services.MapperService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest(classes = {ObjectMapper.class, MapperService.class, LocationService.class})
class LocationServiceTest {
    @Autowired
    LocationService locationService;

    @Autowired
    MapperService mapperService;

    @Test
    void shouldReturnCountriesWhenGettingCountriesFromLocations() {
        final var locations = new ArrayList<FlatLocation>();
        locations.add(new FlatLocation(new Location("BR", "Brazil"), new Location("SP", "São Paulo"), new Location("SA", "Santo André")));
        locations.add(new FlatLocation(new Location("US", "United States"), new Location("CA", "California"), new Location("LA", "Los Angeles")));
        locations.add(new FlatLocation(new Location("US", "United States"), new Location("CA", "California"), new Location("SF", "San Francisco")));
        locations.add(new FlatLocation(new Location("US", "United States"), new Location("TX", "Texas"), new Location("HOU", "Houston")));
        final var countries = locationService.getCountries(locations);
        Assertions.assertNotNull(countries);
        System.out.printf(mapperService.toJson(countries));
    }
}
