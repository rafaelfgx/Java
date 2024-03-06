package architecture.group;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Person {
    private String passport;
    private LocalDate passportExpirationDate;
    private String driverLicense;
    private LocalDate driverLicenseExpirationDate;
}
