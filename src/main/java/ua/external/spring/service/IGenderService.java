package ua.external.spring.service;

import ua.external.spring.entity.Gender;

import java.util.List;
import java.util.Optional;

public interface IGenderService {
    Optional<Gender> findGenderById(Long id);

    List<Gender> findAllGenders();

    boolean create(Gender gender);
}
