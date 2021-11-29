package ua.external.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.external.spring.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false " +
            "END FROM User u WHERE u.login = :login")
    boolean existsByLogin(@Param("login") String login);

    @Query("SELECT u FROM User u where u.login = :login")
    Optional<User> findByLogin(@Param("login") String login);
}
