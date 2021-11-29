package ua.external.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.external.spring.entity.EatPeriod;

public interface EatPeriodRepository extends JpaRepository<EatPeriod, Long> {
}
