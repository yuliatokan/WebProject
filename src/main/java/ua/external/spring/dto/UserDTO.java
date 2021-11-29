package ua.external.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.external.spring.entity.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    private UserRole role;

    public static UserDTO of(Long id, String login, String password, UserRole role) {
        return new UserDTO(id, login, password, role);
    }
}
