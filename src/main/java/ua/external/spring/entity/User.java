package ua.external.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import ua.external.spring.dto.MealsDTO;
import ua.external.spring.dto.UserDTO;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "users")
public class User implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.client = null;
    }

    public static User of(Long id, String login, String password, UserRole role) {
        return User.builder()
                .id(id)
                .login(login)
                .password(password)
                .role(role)
                .build();
    }

    public static User fromDTO(UserDTO userDTO) {
        return User.of(userDTO.getId(), userDTO.getLogin(), userDTO.getPassword(), userDTO.getRole());
    }

    public UserDTO toDTO() {
        return UserDTO.of(id, login, null, role);
    }
}
