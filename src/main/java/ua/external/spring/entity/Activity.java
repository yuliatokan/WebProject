package ua.external.spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "activities")
public class Activity implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "activity")
    private String activity;

    @Column(name = "coefficient")
    private Double coefficient;

    public Activity(String activity, Double coefficient) {
        this.activity = activity;
        this.coefficient = coefficient;
    }
}
