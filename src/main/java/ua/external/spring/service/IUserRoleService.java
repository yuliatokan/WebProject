package ua.external.spring.service;

import ua.external.spring.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface IUserRoleService {
    Optional<UserRole> findUserRoleById(Long id);

    List<UserRole> findAllUserRoles();

    Optional<UserRole> findUserRoleByName(String role);

    boolean create(UserRole role);
}
