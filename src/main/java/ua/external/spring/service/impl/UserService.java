package ua.external.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.external.spring.dto.MealsDTO;
import ua.external.spring.dto.UserDTO;
import ua.external.spring.entity.User;
import ua.external.spring.repository.UserRepository;
import ua.external.spring.service.IUserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealsService mealsService;

    @Override
    @Transactional
    public boolean createUser(UserDTO user) {
        if (userRepository.existsByLogin(user.getLogin()))
            return false;

        userRepository.save(User.fromDTO(user));
        return true;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countCalories(User user) {
        List<MealsDTO> meals = mealsService.getAllMealForUserByDate(user.getId(), LocalDate.now());
        Integer numOfCalories = meals.stream().mapToInt(x -> x.getProduct().getCalories() * x.getWeight() / 100).sum();
        return numOfCalories;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countProtein(User user) {
        List<MealsDTO> meals = mealsService.getAllMealForUserByDate(user.getId(), LocalDate.now());
        Integer numOfProtein = meals.stream()
                .mapToInt(x -> x.getProduct().getProtein().intValue() * x.getWeight() / 100).sum();
        return numOfProtein;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countFats(User user) {
        List<MealsDTO> meals = mealsService.getAllMealForUserByDate(user.getId(), LocalDate.now());
        Integer numOfFats = meals.stream().mapToInt(x -> x.getProduct().getFats().intValue() * x.getWeight() / 100)
                .sum();
        return numOfFats;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countCarbohydrates(User user) {
        List<MealsDTO> meals = mealsService.getAllMealForUserByDate(user.getId(), LocalDate.now());
        Integer numOfCarbohydrates = meals.stream()
                .mapToInt(x -> x.getProduct().getCarbohydrates().intValue() * x.getWeight() / 100).sum();
        return numOfCarbohydrates;
    }
}
