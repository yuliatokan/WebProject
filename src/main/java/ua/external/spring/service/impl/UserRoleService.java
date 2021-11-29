package ua.external.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.external.spring.entity.UserRole;
import ua.external.spring.repository.UserRoleRepository;
import ua.external.spring.service.IUserRoleService;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService implements IUserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRole> findUserRoleById(Long id) {
        return userRoleRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRole> findAllUserRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRole> findUserRoleByName(String role) {
        return userRoleRepository.findByName(role);
    }

    @Override
    @Transactional
    public boolean create(UserRole role) {
        userRoleRepository.save(role);
        return true;
    }
}
