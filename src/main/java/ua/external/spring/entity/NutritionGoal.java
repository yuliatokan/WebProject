package ua.external.spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "nutritionGoals")
public class NutritionGoal implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "goal")
    private String goal;

    @Column(name = "coefficient")
    private Double coefficient;

    public NutritionGoal(String goal, Double coefficient) {
        this.goal = goal;
        this.coefficient = coefficient;
    }
}
