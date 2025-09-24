package com.company.architecture.shared.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ValidatorService.class})
class ValidatorServiceTest {
    @Autowired
    ValidatorService validatorService;

    @Test
    void shouldReturnNotNullWhenValidatingNonNullInput() {
        Assertions.assertNotNull(validatorService.validate("Test"));
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
        "000.000.000-00 | false",
        "012.345.678-99 | false",
        "098.765.432-10 | false",
        "109.876.543-21 | false",
        "111.111.111-11 | false",
        "111.222.333-44 | false",
        "123.456.789-00 | false",
        "210.987.654-32 | false",
        "222.222.222-22 | false",
        "222.333.444-55 | false",
        "321.098.765-43 | false",
        "333.333.333-33 | false",
        "333.444.555-66 | false",
        "432.109.876-54 | false",
        "444.444.444-44 | false",
        "444.555.666-77 | false",
        "543.210.987-65 | false",
        "555.555.555-55 | false",
        "555.666.777-88 | false",
        "654.321.098-76 | false",
        "666.666.666-66 | false",
        "666.777.888-99 | false",
        "765.432.109-87 | false",
        "777.777.777-77 | false",
        "777.888.999-00 | false",
        "876.543.210-98 | false",
        "888.888.888-88 | false",
        "888.999.000-11 | false",
        "987.654.321-09 | false",
        "999.999.999-99 | false",
        "019.369.987-77 | true",
        "099.994.960-83 | true",
        "119.250.043-17 | true",
        "123.456.789-09 | true",
        "124.977.481-01 | true",
        "186.204.852-53 | true",
        "209.849.468-88 | true",
        "252.912.234-21 | true",
        "258.531.001-90 | true",
        "268.366.204-16 | true",
        "373.665.374-38 | true",
        "375.770.937-34 | true",
        "425.941.647-20 | true",
        "426.239.429-86 | true",
        "434.373.227-45 | true",
        "445.932.090-80 | true",
        "447.203.862-53 | true",
        "448.704.322-00 | true",
        "459.382.535-00 | true",
        "460.941.584-40 | true",
        "465.653.828-08 | true",
        "469.728.692-85 | true",
        "500.555.100-00 | true",
        "525.582.629-47 | true",
        "584.703.579-99 | true",
        "652.190.061-77 | true",
        "655.360.304-93 | true",
        "668.745.850-70 | true",
        "678.202.816-69 | true",
        "685.632.673-45 | true",
        "688.669.898-27 | true",
        "702.591.498-37 | true",
        "706.506.942-79 | true",
        "712.257.404-01 | true",
        "722.521.918-99 | true",
        "727.797.500-65 | true",
        "739.485.124-93 | true",
        "760.695.875-02 | true",
        "773.121.062-69 | true",
        "786.008.411-27 | true",
        "796.587.368-07 | true",
        "808.841.252-89 | true",
        "814.506.847-93 | true",
        "850.741.787-62 | true",
        "875.375.009-83 | true",
        "882.226.664-10 | true",
        "890.049.917-35 | true",
        "924.006.403-60 | true",
        "942.022.943-27 | true",
        "959.596.181-76 | true"
    })
    void shouldReturnCorrectValidationResultForCpf(String value, boolean valid) {
        Assertions.assertEquals(valid, validatorService.validateCpf(value));
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
        "00.111.222/0001-38 | false",
        "01.234.567/0001-21 | false",
        "12.345.678/0001-91 | false",
        "23.456.789/0001-75 | false",
        "33.444.555/0001-67 | false",
        "45.678.901/0001-44 | false",
        "55.666.777/0001-51 | false",
        "67.890.123/0001-03 | false",
        "89.012.345/0001-62 | false",
        "99.000.111/0001-53 | false",
        "30.054.757/0001-29 | true",
        "47.643.621/0001-57 | true",
        "57.863.764/0001-28 | true",
        "58.524.873/0001-83 | true",
        "65.844.157/0001-49 | true",
        "67.072.607/0001-58 | true",
        "68.283.473/0001-87 | true",
        "74.730.115/0001-78 | true",
        "77.516.151/0001-21 | true",
        "80.373.001/0001-10 | true"
    })
    void shouldReturnCorrectValidationResultForCnpj(String value, boolean valid) {
        Assertions.assertEquals(valid, validatorService.validateCnpj(value));
    }
}
