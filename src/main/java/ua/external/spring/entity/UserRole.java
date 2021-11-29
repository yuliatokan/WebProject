package ua.external.spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class UserRole implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;


    public UserRole(@NonNull String role) {
        this.role = role;
    }
}
