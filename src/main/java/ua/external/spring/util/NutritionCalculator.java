package ua.external.spring.util;

import ua.external.spring.entity.Activity;
import ua.external.spring.entity.Gender;
import ua.external.spring.entity.NutritionGoal;

/**
 * The {@code NutritionCalculator} class contains methods for
 * calculating daily nutrition norm.
 */
public class NutritionCalculator {
    public static final Integer AVR_CALORIES = 1800;

    public static Integer calculateBaseCalories(Gender gender, Double weight, Double height, Integer age) {
        if (gender.getGender().equals("MALE")) {
            return (int) (10 * weight + 6.25 * height - 5 * age + 5);
        } else {
            return (int) (10 * weight + 6.25 * height - 5 * age - 161);
        }
    }

    public static Integer calculateDailyCaloriesByActivity(Activity activity, Integer calories) {
        Integer resultCalories = calories;

        resultCalories = (int) (activity.getCoefficient() * calories);

        return resultCalories;
    }

    public static Integer calculateDailyCaloriesByNutritionGoal(NutritionGoal nutritionGoal, Integer calories) {
        Integer resultCalories = calories;

        resultCalories = (int) (nutritionGoal.getCoefficient() * calories);

        return resultCalories;
    }

    public static Integer calculateCalories(Gender gender, Double weight, Double height, Integer age, Activity activity, NutritionGoal nutritionGoal) {
        Integer calories = calculateBaseCalories(gender, weight, height, age);
        calories = calculateDailyCaloriesByActivity(activity, calories);
        return calculateDailyCaloriesByNutritionGoal(nutritionGoal, calories);
    }

    public static Double calculateProtein(Integer calories) {
        final Double maintenanceCoefficient = 0.2;
        return maintenanceCoefficient * calories;
    }

    public static Double calculateProtein(NutritionGoal nutritionGoal, Integer calories) {
        final Double lostCoefficient = 0.3;
        final Double maintenanceCoefficient = 0.2;
        final Double gainCoefficient = 0.14;

        Double resultProtein = maintenanceCoefficient * calories;

        switch (nutritionGoal.getGoal()) {
            case "WEIGHT_LOST":
                resultProtein = lostCoefficient * calories;
                break;
            case "WEIGHT_MAINTENANCE":
                resultProtein = maintenanceCoefficient * calories;
                break;
            case "WEIGHT_GAIN":
                resultProtein = gainCoefficient * calories;
                break;
        }

        return resultProtein;
    }

    public static Double calculateFats(Integer calories) {
        final Double maintenanceCoefficient = 0.3;
        return maintenanceCoefficient * calories;
    }

    public static Double calculateFats(NutritionGoal nutritionGoal, Integer calories) {
        final Double lostCoefficient = 0.2;
        final Double maintenanceCoefficient = 0.3;
        final Double gainCoefficient = 0.3;

        Double resultFats = maintenanceCoefficient * calories;

        switch (nutritionGoal.getGoal()) {
            case "WEIGHT_LOST":
                resultFats = lostCoefficient * calories;
                break;
            case "WEIGHT_MAINTENANCE":
                resultFats = maintenanceCoefficient * calories;
                break;
            case "WEIGHT_GAIN":
                resultFats = gainCoefficient * calories;
                break;
        }

        return resultFats;
    }

    public static Double calculateCarbohydrates(Integer calories) {
        final Double maintenanceCoefficient = 0.6;
        return maintenanceCoefficient * calories;
    }

    public static Double calculateCarbohydrates(NutritionGoal nutritionGoal, Integer calories) {
        final Double lostCoefficient = 0.5;
        final Double maintenanceCoefficient = 0.6;
        final Double gainCoefficient = 0.56;

        Double resultCarbohydrates = maintenanceCoefficient * calories;

        switch (nutritionGoal.getGoal()) {
            case "WEIGHT_LOST":
                resultCarbohydrates = lostCoefficient * calories;
                break;
            case "WEIGHT_MAINTENANCE":
                resultCarbohydrates = maintenanceCoefficient * calories;
                break;
            case "WEIGHT_GAIN":
                resultCarbohydrates = gainCoefficient * calories;
                break;
        }

        return resultCarbohydrates;
    }
}
