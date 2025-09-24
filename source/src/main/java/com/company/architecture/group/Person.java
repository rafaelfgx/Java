package com.company.architecture.group;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {
    private String passport;
    private LocalDate passportExpirationDate;
    private String driverLicense;
    private LocalDate driverLicenseExpirationDate;
}
