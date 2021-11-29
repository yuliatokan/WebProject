package ua.external.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.external.spring.entity.Activity;
import ua.external.spring.entity.Gender;
import ua.external.spring.entity.NutritionGoal;
import ua.external.spring.util.NutritionCalculator;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 32)
    private String name;

    private Gender gender;

    @NotNull
    @Min(value = 10)
    @Max(value = 99)
    private Integer age;

    @NotNull
    @Min(value = 90)
    @Max(value = 220)
    private Double height;

    @NotNull
    @Min(value = 30)
    @Max(value = 250)
    private Double weight;

    private NutritionGoal nutritionGoal;

    private Activity activity;

    private Integer calories;
    private Double protein;
    private Double fats;
    private Double carbohydrates;

    public static ClientDTO of(Long id, String name, Gender gender, Integer age, Double height, Double weight,
                               NutritionGoal nutritionGoal, Activity activity, Integer calories, Double protein,
                               Double fats, Double carbohydrates) {
        return new ClientDTO(id, name, gender, age, height, weight, nutritionGoal, activity, calories, protein, fats,
                carbohydrates);
    }

    public void calculateNutritions() {
        Integer dailyCalories = NutritionCalculator
                .calculateCalories(gender, weight, height, age, activity, nutritionGoal);
        this.calories = dailyCalories;
        this.protein = NutritionCalculator.calculateProtein(nutritionGoal, dailyCalories);
        this.fats = NutritionCalculator.calculateFats(nutritionGoal, dailyCalories);
        this.carbohydrates = NutritionCalculator.calculateCarbohydrates(nutritionGoal, dailyCalories);
    }
}
