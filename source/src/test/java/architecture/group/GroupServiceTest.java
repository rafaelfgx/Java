package architecture.group;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { GroupService.class })
class GroupServiceTest {
    @Autowired
    GroupService groupService;

    @Test
    void testGroupPropertiesByInvalid() {
        final var date = LocalDate.of(2100, 1, 1);
        final var person = new Person();
        person.setPassport("12345");
        person.setPassportExpirationDate(date);
        person.setDriverLicense("67890");
        person.setDriverLicenseExpirationDate(date);

        final var groups = groupService.groupPropertiesBy(person, "Invalid");

        Assertions.assertEquals(0, groups.size());
    }

    @Test
    void testGroupPropertiesBySameDate() {
        final var date = LocalDate.of(2100, 1, 1);
        final var person = new Person();
        person.setPassport("12345");
        person.setPassportExpirationDate(date);
        person.setDriverLicense("67890");
        person.setDriverLicenseExpirationDate(date);

        final var groups = groupService.groupPropertiesBy(person, "ExpirationDate");
        final var values = groups.get(date).values().toArray();

        Assertions.assertEquals(1, groups.size());
        Assertions.assertEquals(person.getPassport(), values[0]);
        Assertions.assertEquals(person.getDriverLicense(), values[1]);
    }

    @Test
    void testGroupPropertiesByDifferentDates() {
        final var person = new Person();
        person.setPassport("12345");
        person.setPassportExpirationDate(LocalDate.of(2100, 1, 1));
        person.setDriverLicense("67890");
        person.setDriverLicenseExpirationDate(LocalDate.of(2200, 1, 1));

        final var groups = groupService.groupPropertiesBy(person, "ExpirationDate");

        Assertions.assertEquals(2, groups.size());
        Assertions.assertEquals(person.getPassport(), groups.get(person.getPassportExpirationDate()).values().toArray()[0]);
        Assertions.assertEquals(person.getDriverLicense(), groups.get(person.getDriverLicenseExpirationDate()).values().toArray()[0]);
    }

    @Test
    void testSetProperties() {
        final var date = LocalDate.of(2100, 1, 1);

        final var person = new Person();
        person.setPassport("ABCDE");
        person.setDriverLicense("FGHIJ");

        final var personUpdate = new Person();
        personUpdate.setPassport("12345");
        personUpdate.setPassportExpirationDate(date);

        final var groups = groupService.groupPropertiesBy(personUpdate, "ExpirationDate");

        groups.forEach((expirationDate, map) -> groupService.setProperties(person, map));

        Assertions.assertEquals("12345", person.getPassport());
        Assertions.assertEquals("FGHIJ", person.getDriverLicense());
    }
}
