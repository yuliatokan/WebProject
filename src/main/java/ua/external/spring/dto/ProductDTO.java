package ua.external.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ua.external.spring.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 32)
    private String name;

    private User user;

    @NonNull
    @Min(value = 0)
    @Max(value = 2000)
    private Integer calories;

    @NonNull
    @Min(value = 0)
    @Max(value = 1000)
    private Double protein;

    @NonNull
    @Min(value = 0)
    @Max(value = 1000)
    private Double fats;

    @Min(value = 0)
    @Max(value = 1000)
    private Double carbohydrates;

    private Boolean common;

    private Boolean deleted;

    public static ProductDTO of(Long id, String name, User user, Integer calories, Double protein, Double fats, Double carbohydrates, Boolean common, Boolean deleted) {
        return new ProductDTO(id, name, user, calories, protein, fats, carbohydrates, common, deleted);
    }
}
