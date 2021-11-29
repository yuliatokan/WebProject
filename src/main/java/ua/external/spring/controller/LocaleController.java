package ua.external.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LocaleController {
    @Autowired
    MessageSource messageSource;

    @GetMapping(value = "/international")
    public String getMealsPage(@RequestParam(name = "lang") String lang, HttpSession session, HttpServletRequest request) {
        session.setAttribute("local", lang);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
