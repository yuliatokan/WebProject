package ua.external.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.external.spring.dto.UserDTO;

import static ua.external.spring.util.сonst.Constant.PARAM_LOGIN;
import static ua.external.spring.util.сonst.Constant.PARAM_USER;
import static ua.external.spring.util.сonst.Pages.*;

import ua.external.spring.service.impl.EmailService;
import ua.external.spring.service.impl.UserRoleService;
import ua.external.spring.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/sign")
public class SignController {
    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    final static Logger logger = LogManager.getLogger();

    @GetMapping(value = "/up")
    public String getSignPage(HttpSession session, Model model) {
        return REGISTRATION_PAGE;
    }

    @PostMapping(value = "/up")
    public String signUp(HttpServletRequest request, HttpSession session, @Valid UserDTO user, Model model) {
        user.setRole(userRoleService.findUserRoleByName("USER").get());

        String password = user.getPassword();
        String passHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passHash);

        if (!userService.createUser(user)) {
            model.addAttribute("error_user", true);
            model.addAttribute(PARAM_LOGIN, user.getLogin());
            return "redirect:/sign/up?error_create";
        }
        authWithAuthManager(request, user.getLogin(), password);
        session.setAttribute(PARAM_USER, user);
        logger.info("create user with id = " + user.getId());
        //emailService.sendWelcomeLetter(user.getLogin());
        return "redirect:/client/info";
    }

    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
