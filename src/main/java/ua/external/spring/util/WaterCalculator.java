package ua.external.spring.util;

import ua.external.spring.entity.Gender;

public class WaterCalculator {
    public static final Integer AVR_WATER = 2000;

    public static Integer calculateWaterMl(Gender gender, Double weight) {
        if (gender.getGender().equals("MALE")) {
            return (int) (31 * weight);
        } else {
            return (int) (35 * weight);
        }
    }
}
