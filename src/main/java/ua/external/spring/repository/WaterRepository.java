package ua.external.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.external.spring.entity.Meals;
import ua.external.spring.entity.User;
import ua.external.spring.entity.Water;

import java.util.List;
import java.util.Optional;

public interface WaterRepository extends JpaRepository<Water, Long> {
    Optional<Water> getWaterByUser(User user);
}
