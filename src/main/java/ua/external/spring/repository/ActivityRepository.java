package ua.external.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.external.spring.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
