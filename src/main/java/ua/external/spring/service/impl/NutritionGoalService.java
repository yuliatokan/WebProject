package ua.external.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.external.spring.entity.NutritionGoal;
import ua.external.spring.repository.NutritionGoalRepository;
import ua.external.spring.service.INutritionGoalService;

import java.util.List;
import java.util.Optional;

@Service
public class NutritionGoalService implements INutritionGoalService {
    @Autowired
    NutritionGoalRepository nutritionGoalRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<NutritionGoal> findNutritionGoalById(Long id) {
        return nutritionGoalRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NutritionGoal> findAllNutritionGoals() {
        return nutritionGoalRepository.findAll();
    }

    @Override
    @Transactional
    public boolean create(NutritionGoal nutritionGoal) {
        nutritionGoalRepository.save(nutritionGoal);
        return true;
    }
}
