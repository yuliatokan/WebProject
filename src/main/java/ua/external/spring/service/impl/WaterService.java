package ua.external.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.external.spring.dto.MealsDTO;
import ua.external.spring.entity.Meals;
import ua.external.spring.entity.User;
import ua.external.spring.entity.Water;
import ua.external.spring.repository.MealsRepository;
import ua.external.spring.repository.WaterRepository;
import ua.external.spring.service.IMealsService;
import ua.external.spring.service.IWaterService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WaterService implements IWaterService {
    @Autowired
    WaterRepository waterRepository;

    @Override
    @Transactional
    public Optional<Water> findWaterByUser(User user) {
        return waterRepository.getWaterByUser(user);
    }

    @Override
    @Transactional
    public boolean addWater(Water water) {
        waterRepository.save(water);
        return true;
    }
}
