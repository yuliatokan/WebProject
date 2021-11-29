package ua.external.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.external.spring.entity.EatPeriod;
import ua.external.spring.repository.EatPeriodRepository;
import ua.external.spring.service.IEatPeriodService;

import java.util.List;
import java.util.Optional;

@Service
public class EatPeriodService implements IEatPeriodService {
    @Autowired
    EatPeriodRepository eatPeriodRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<EatPeriod> findEatPeriodById(Long id) {
        return eatPeriodRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EatPeriod> findAllEatPeriods() {
        return eatPeriodRepository.findAll();
    }

    @Override
    @Transactional
    public boolean create(EatPeriod eatPeriod) {
        eatPeriodRepository.save(eatPeriod);
        return true;
    }
}
