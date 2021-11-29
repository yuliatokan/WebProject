package ua.external.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.external.spring.entity.NutritionGoal;

public interface NutritionGoalRepository extends JpaRepository<NutritionGoal, Long> {
}
