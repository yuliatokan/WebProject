package ua.external.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.external.spring.dto.ClientDTO;
import ua.external.spring.entity.*;
import ua.external.spring.service.impl.*;

import static ua.external.spring.util.сonst.Constant.*;
import static ua.external.spring.util.сonst.Pages.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    private GenderService genderService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private NutritionGoalService nutritionGoalService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    final static Logger logger = LogManager.getLogger();

    @GetMapping(value = "/info")
    public String getInfoPage(HttpSession session, Model model) {
        return CL_INFO_PAGE;
    }

    @GetMapping(value = "/edit")
    public String getEditPage(HttpSession session, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            if (user.getClient() != null) {
                return CL_EDIT_PAGE;
            } else {
                return "redirect:/client/info";
            }
        }
        return "redirect:/";
    }

    @PostMapping(value = "/info")
    public String clientInfo(HttpServletRequest request, @Valid ClientDTO client) {
        buildClient(request, client);
        Client newCL = clientService.createClient(client);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            user.setClient(newCL);
            userService.updateUser(user);
            logger.info("create client information for user with id=" + user.getId());
        }
        return "redirect:/";
    }

    @PostMapping(value = "/edit")
    public String clientEdit(HttpServletRequest request, @Valid ClientDTO client, HttpSession session) {
        buildClient(request, client);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            client.setId(user.getClient().getId());
        }
        clientService.updateClient(client);
        logger.info("update client information with id=" + client.getId());
        return "redirect:/";
    }

    private void buildClient(HttpServletRequest request, ClientDTO clientDTO) {
        Long gender_id = Long.parseLong(request.getParameter(PARAM_GENDER));
        Long activity_id = Long.parseLong(request.getParameter(PARAM_ACTIVITY));
        Long nutritionGoal_id = Long.parseLong(request.getParameter(PARAM_NUTRITION_GOAL));

        Gender gender = genderService.findGenderById(gender_id).get();
        Activity activity = activityService.findActivityById(activity_id).get();
        NutritionGoal nutritionGoal = nutritionGoalService.findNutritionGoalById(nutritionGoal_id).get();

        clientDTO.setGender(gender);
        clientDTO.setActivity(activity);
        clientDTO.setNutritionGoal(nutritionGoal);
        clientDTO.calculateNutritions();
    }
}
