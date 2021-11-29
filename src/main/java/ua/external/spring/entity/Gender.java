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
@Table(name = "genders")
public class Gender implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @NonNull
    @Column(name = "gender")
    private String gender;

    public Gender(@NonNull String gender) {
        this.gender = gender;
    }
}
