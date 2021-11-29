package ua.external.spring.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.external.spring.dto.MealsDTO;
import ua.external.spring.entity.EatPeriod;
import ua.external.spring.entity.User;
import ua.external.spring.service.impl.EatPeriodService;
import ua.external.spring.service.impl.MealsService;
import ua.external.spring.service.impl.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RestMealsController {
    @Autowired
    private MealsService mealsService;

    @Autowired
    private UserService userService;

    @Autowired
    private EatPeriodService eatPeriodService;

    @GetMapping(value = "/get_meals")
    public Map<String, List<String>> getMeals(HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            List<MealsDTO> meals = mealsService.getAllMealForUserByDate(user.getId(), LocalDate.now());
            return getStructuresInfo(meals);
        }
        return new LinkedHashMap<>();
    }

    private Map<String, List<String>> getStructuresInfo(List<MealsDTO> meals) {
        List<EatPeriod> eatPeriods = eatPeriodService.findAllEatPeriods();
        Map<String, List<String>> structMeals = new LinkedHashMap<>();

        ObjectMapper Obj = new ObjectMapper();

        for (EatPeriod eatPeriod : eatPeriods) {
            List<MealsDTO> mealsByTime = meals.stream().filter(x -> x.getEatPeriod().getId() == eatPeriod.getId()).collect(Collectors.toList());
            List<String> result = new ArrayList<>();
            for (MealsDTO m : mealsByTime) {
                try {
                    result.add(Obj.writeValueAsString(m));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            structMeals.put(eatPeriod.getPeriod(), result);
        }
        return structMeals;
    }
}
