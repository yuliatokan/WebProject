package ua.external.spring.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "eatPeriods")
public class EatPeriod implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @NonNull
    @Column(name = "period")
    private String period;

    public EatPeriod(@NonNull String period) {
        this.period = period;
    }
}
