package ua.external.spring.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.external.spring.dto.ClientDTO;
import ua.external.spring.entity.*;
import ua.external.spring.service.impl.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestInfoController {
    @Autowired
    private GenderService genderService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private NutritionGoalService nutritionGoalService;

    @Autowired
    private UserService userService;

    @Autowired
    private EatPeriodService eatPeriodService;

    @GetMapping(value = "/get_genders")
    public List<Gender> getGenders() {
        return genderService.findAllGenders();
    }

    @GetMapping(value = "/get_activities")
    public List<Activity> getActivities() {
        return activityService.findAllActivities();
    }

    @GetMapping(value = "/get_nutr_goals")
    public List<NutritionGoal> getNutrGoals() {
        return nutritionGoalService.findAllNutritionGoals();
    }

    @GetMapping(value = "/get_eat_periods")
    public List<EatPeriod> getEatPeriods() {
        List<EatPeriod> eatPeriods = eatPeriodService.findAllEatPeriods();
        return eatPeriodService.findAllEatPeriods();
    }

    @GetMapping(value = "/get_client_info")
    public ClientDTO getClient(HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            Client client = user.getClient();
            return client == null ? null : client.toDTO();
        }
        return null;
    }

    @GetMapping(value = "/is_login")
    public boolean isLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return true;
        }
        return false;
    }

    @GetMapping(value = "/is_admin")
    public boolean isAdmin(HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            return user.getRole().getRole().equals("ADMIN");
        }
        return false;
    }

    @GetMapping(value = "/get_progress")
    public Map<String, Integer> getProgress() {
        Map<String, Integer> progress = new HashMap<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            progress.put("currentCalories", userService.countCalories(user));
            progress.put("currentProtein", userService.countProtein(user));
            progress.put("currentFats", userService.countFats(user));
            progress.put("currentCarbohydrates", userService.countCarbohydrates(user));
        }
        return progress;
    }
}
