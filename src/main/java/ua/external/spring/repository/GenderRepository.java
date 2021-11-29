package ua.external.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.external.spring.entity.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}
