package ua.external.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.external.spring.entity.Meals;
import ua.external.spring.entity.Product;

import java.util.List;

public interface MealsRepository extends JpaRepository<Meals, Long> {
    @Query("SELECT m FROM Meals m where m.user.id = :id")
    List<Meals> findAllByUser(@Param("id") Long id);

    @Query("SELECT m FROM Meals m WHERE m.user.id = :id AND TO_CHAR(date, 'YYYY-MM-DD') = :curr_date")
    List<Meals> findAllByUserAndDay(@Param("id") Long id, @Param("curr_date") String curr_date);
}
