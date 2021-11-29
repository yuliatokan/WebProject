package ua.external.spring.service;


import org.springframework.transaction.annotation.Transactional;
import ua.external.spring.entity.User;
import ua.external.spring.entity.Water;

import java.util.Optional;

public interface IWaterService {
    Optional<Water> findWaterByUser(User user);

    @Transactional
    boolean addWater(Water water);
}
