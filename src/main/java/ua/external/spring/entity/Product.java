package ua.external.spring.entity;

import lombok.*;
import ua.external.spring.dto.ProductDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "products")
public class Product implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "fats")
    private Double fats;

    @Column(name = "carbohydrates")
    private Double carbohydrates;

    @Column(name = "common")
    private Boolean common;

    @Column(name = "deleted")
    private Boolean deleted;

    public static Product of(Long id, String name, User user, Integer calories, Double protein, Double fats, Double carbohydrates, Boolean common, Boolean deleted){
        return Product.builder()
                .id(id)
                .name(name)
                .user(user)
                .calories(calories)
                .protein(protein)
                .fats(fats)
                .carbohydrates(carbohydrates)
                .common(common)
                .deleted(deleted)
                .build();
    }

    public static Product fromDTO(ProductDTO productDTO){
        return Product.of(productDTO.getId(), productDTO.getName(), productDTO.getUser(), productDTO.getCalories(), productDTO.getProtein(), productDTO.getFats(), productDTO.getCarbohydrates(), productDTO.getCommon(), productDTO.getDeleted());
    }

    public ProductDTO toDTO (){
        return ProductDTO.of(id, name,null, calories, protein, fats, carbohydrates, common, deleted);
    }
}
