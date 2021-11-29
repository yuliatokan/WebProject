package ua.external.spring.validator;

import java.util.regex.Pattern;

/**
 * The {@code DataValidator} class contains methods for
 * validating an input data (data from forms).
 */
public class DataValidator {
    private final static String REGEX_CHECK_FOR_LOGIN_AS_EMAIL = "^([\\w\\-\\.]+)@([\\w\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private final static String REGEX_CHECK_FOR_PASSWORD = "^([\\wа-яА-Я]{7,20})$";
    private final static String REGEX_CHECK_FOR_NAME = "^[a-zA-Zа-яА-Я\\\\s]{2,20}$";

    private final static Integer MIN_AGE = 10;
    private final static Integer MAX_AGE = 100;
    private final static Integer MIN_HEIGHT = 90;
    private final static Integer MAX_HEIGHT = 250;
    private final static Integer MIN_WEIGHT = 35;
    private final static Integer MAX_WEIGHT = 300;
    private final static Integer MIN_PRODUCT_WEIGHT = 1;
    private final static Integer MAX_PRODUCT_WEIGHT = 2000;
    private final static Integer MIN_CALORIES = 1;
    private final static Integer MAX_CALORIES = 2000;
    private final static Integer MIN_VALUE_NUTRITION = 0;
    private final static Integer MAX_VALUE_NUTRITION = 2000;

    public static boolean validateLogin(String login) {
        return Pattern.matches(REGEX_CHECK_FOR_LOGIN_AS_EMAIL, login);
    }

    public static boolean validatePassword(String password) {
        return Pattern.matches(REGEX_CHECK_FOR_PASSWORD, password);
    }

    public static boolean validateName(String name) {
        return Pattern.matches(REGEX_CHECK_FOR_NAME, name);
    }

    public static boolean validateAge(Integer age) {
        return age >= MIN_AGE && age <= MAX_AGE;
    }

    public static boolean validateHeight(Double height) {
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT;
    }

    public static boolean validateWeight(Double weight) {
        return weight >= MIN_WEIGHT && weight <= MAX_WEIGHT;
    }

    public static boolean validateProductWeight(Integer weight) {
        return weight >= MIN_PRODUCT_WEIGHT && weight <= MAX_PRODUCT_WEIGHT;
    }

    public static boolean validateCalories(Integer calories) {
        return calories >= MIN_CALORIES && calories <= MAX_CALORIES;
    }

    public static boolean validateNutrition(Double nutrition) {
        return nutrition >= MIN_VALUE_NUTRITION && nutrition <= MAX_VALUE_NUTRITION;
    }
}
