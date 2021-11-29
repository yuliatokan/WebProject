package ua.external.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.external.spring.entity.UserRole;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("SELECT r FROM UserRole r where r.role = :role")
    Optional<UserRole> findByName(@Param("role") String role);
}
