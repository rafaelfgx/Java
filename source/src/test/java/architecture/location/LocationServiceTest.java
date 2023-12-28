package architecture.location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { ObjectMapper.class, LocationService.class })
class LocationServiceTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    LocationService locationService;

    @Test
    void testGetCountries() throws JsonProcessingException {
        final var locations = new ArrayList<FlatLocation>();
        locations.add(new FlatLocation(new Location("BR", "Brazil"), new Location("SP", "São Paulo"), new Location("SA", "Santo André")));
        locations.add(new FlatLocation(new Location("US", "United States"), new Location("CA", "California"), new Location("LA", "Los Angeles")));
        locations.add(new FlatLocation(new Location("US", "United States"), new Location("CA", "California"), new Location("SF", "San Francisco")));
        locations.add(new FlatLocation(new Location("US", "United States"), new Location("TX", "Texas"), new Location("HOU", "Houston")));
        final var countries = locationService.getCountries(locations);
        Assertions.assertNotNull(countries);
        System.out.printf(objectMapper.writeValueAsString(countries));
    }
}
