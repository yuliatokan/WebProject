package ua.external.spring.service;

import ua.external.spring.dto.MealsDTO;

import java.time.LocalDate;
import java.util.List;

public interface IMealsService {
    boolean createMeals(MealsDTO meals);

    List<MealsDTO> getAllMealForUser(Long userId);

    boolean deleteMealsById(Long id);

    List<MealsDTO> getAllMealForUserByDate(Long userId, LocalDate date);
}
