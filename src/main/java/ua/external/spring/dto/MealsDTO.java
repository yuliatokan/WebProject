package ua.external.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.external.spring.entity.EatPeriod;
import ua.external.spring.entity.Product;
import ua.external.spring.entity.User;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealsDTO {

    private Long id;

    private Product product;

    private User user;

    private Integer weight;

    private EatPeriod eatPeriod;

    private Timestamp date;

    public static MealsDTO of(Long id, Product product, User user, Integer weight, EatPeriod eatPeriod, Timestamp date) {
        return new MealsDTO(id, product, user, weight, eatPeriod, date);
    }
}
