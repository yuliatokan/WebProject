package ua.external.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.external.spring.dto.ClientDTO;
import ua.external.spring.util.NutritionCalculator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "clients")
public class Client implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "nutritionGoal_id")
    private NutritionGoal nutritionGoal;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "fats")
    private Double fats;

    @Column(name = "carbohydrates")
    private Double carbohydrates;

    public Client(String name, Gender gender, Integer age, Double height, Double weight, NutritionGoal nutritionGoal, Activity activity) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.nutritionGoal = nutritionGoal;
        this.activity = activity;

        //calculate calories ...
        Integer dailyCalories = NutritionCalculator.calculateCalories(gender, weight, height, age, activity, nutritionGoal);
        this.calories = dailyCalories;
        this.protein = NutritionCalculator.calculateProtein(nutritionGoal, dailyCalories);
        this.fats = NutritionCalculator.calculateFats(nutritionGoal, dailyCalories);
        this.carbohydrates = NutritionCalculator.calculateCarbohydrates(nutritionGoal, dailyCalories);
    }

    public static Client of(Long id, String name, Gender gender, Integer age, Double height, Double weight, NutritionGoal nutritionGoal, Activity activity, Integer calories, Double protein, Double fats, Double carbohydrates) {
        return Client.builder()
                .id(id)
                .name(name)
                .gender(gender)
                .age(age)
                .height(height)
                .weight(weight)
                .nutritionGoal(nutritionGoal)
                .activity(activity)
                .calories(calories)
                .protein(protein)
                .fats(fats)
                .carbohydrates(carbohydrates)
                .build();
    }

    public static Client fromDTO(ClientDTO clientDTO) {
        return Client.of(clientDTO.getId(), clientDTO.getName(), clientDTO.getGender(), clientDTO.getAge(), clientDTO.getHeight(), clientDTO.getWeight(), clientDTO.getNutritionGoal(), clientDTO.getActivity(), clientDTO.getCalories(), clientDTO.getProtein(), clientDTO.getFats(), clientDTO.getCarbohydrates());
    }

    public ClientDTO toDTO() {
        return ClientDTO.of(id, name, gender, age, height, weight, nutritionGoal, activity, calories, protein, fats, carbohydrates);
    }
}
