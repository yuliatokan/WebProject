package ua.external.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.external.spring.dto.MealsDTO;
import ua.external.spring.entity.Meals;
import ua.external.spring.repository.MealsRepository;
import ua.external.spring.service.IMealsService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealsService implements IMealsService {
    @Autowired
    MealsRepository mealsRepository;

    @Override
    @Transactional
    public boolean createMeals(MealsDTO meals) {
        mealsRepository.save(Meals.fromDTO(meals));
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MealsDTO> getAllMealForUser(Long userId) {
        final List<MealsDTO> result = new ArrayList<>();
        List<Meals> meals = mealsRepository.findAllByUser(userId);

        meals.forEach((x) -> result.add(x.toDTO()));
        return result;
    }

    @Override
    @Transactional
    public boolean deleteMealsById(Long id) {
        mealsRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MealsDTO> getAllMealForUserByDate(Long userId, LocalDate date) {
        final List<MealsDTO> result = new ArrayList<>();
        List<Meals> meals = mealsRepository.findAllByUserAndDay(userId, date.toString());
        meals.forEach((x) -> result.add(x.toDTO()));
        return result;
    }
}
