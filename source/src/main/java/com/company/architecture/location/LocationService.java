package com.company.architecture.location;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private static List<State> getStates(final List<FlatLocation> locations, final Country country) {
        return locations.stream().filter(location -> location.getCountry().getCode().equals(country.getCode())).map(FlatLocation::getState).distinct().map(state -> {
            final var newState = new State();
            newState.setCode(state.getCode());
            newState.setName(state.getName());
            newState.setCities(getCities(locations, newState));
            return newState;
        }).toList();
    }

    private static List<City> getCities(final List<FlatLocation> locations, final State state) {
        return locations.stream().filter(location -> location.getState().getCode().equals(state.getCode())).map(FlatLocation::getCity).distinct().map(city -> {
            final var newCity = new City();
            newCity.setCode(city.getCode());
            newCity.setName(city.getName());
            return newCity;
        }).toList();
    }

    public List<Country> getCountries(final List<FlatLocation> locations) {
        return locations.stream().map(FlatLocation::getCountry).distinct().map(country -> {
            final var newCountry = new Country();
            newCountry.setCode(country.getCode());
            newCountry.setName(country.getName());
            newCountry.setStates(getStates(locations, newCountry));
            return newCountry;
        }).toList();
    }
}
