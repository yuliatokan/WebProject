package ua.external.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.external.spring.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE (p.user.id = :id OR p.common = true) AND p.deleted = false")
    List<Product> findAllByUser(@Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE (p.user.id = :id OR p.common = true) AND p.deleted = false AND p.name LIKE :name")
    List<Product> findAllByNameForUser(@Param("id") Long id, @Param("name") String name);

    @Query("SELECT COUNT(p.id) FROM Product p WHERE (p.user.id = :id or p.common = true) AND p.deleted = false")
     int getNumberOfRows(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Product p SET p.deleted= true WHERE p.id = :id")
    void updateDeletedProductById(@Param("id") Long id);

    @Query("SELECT m.product FROM Meals m WHERE m.user.id = :id AND TO_CHAR(date, 'YYYY-MM-DD') = :curr_date")
    List<Product> findAllByUserAndDay(@Param("id") Long id, @Param("curr_date") String curr_date);

    @Query(value = "SELECT p FROM Product p WHERE (p.user.id = :id OR p.common = true) AND p.deleted = false")
    Page<Product> findUserProductsByPage(@Param("id") Long id, Pageable pageable);
}
