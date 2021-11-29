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
import org.springframework.web.bind.annotation.RequestParam;
import ua.external.spring.dto.ProductDTO;
import ua.external.spring.entity.User;
import ua.external.spring.service.impl.ProductService;
import ua.external.spring.service.impl.UserService;

import static ua.external.spring.util.сonst.Pages.*;

import static ua.external.spring.util.сonst.Constant.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    final static Logger logger = LogManager.getLogger();

    @GetMapping(value = "/products")
    public String adminProducts(HttpSession session, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            int numOfProducts = productService.getNumberOfProducts(user.getId());
            final int TOTAL_PAGES;
            if (numOfProducts % PAGE_SIZE == 0) {
                TOTAL_PAGES = numOfProducts / PAGE_SIZE;
            } else {
                TOTAL_PAGES = numOfProducts / PAGE_SIZE + 1;
            }
            model.addAttribute(PARAM_PAGES, TOTAL_PAGES);
        }
        return ADMIN_MEALS_PAGE;
    }

    @PostMapping(value = "/product/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        productService.deleteProductById(id);
        logger.info("delete product with id = " + id);
        return ADMIN_MEALS_PAGE;
    }

    @PostMapping(value = "/product/edit")
    public String edit(HttpServletRequest request, @Valid ProductDTO product) {
        Boolean isPublic = request.getParameter(PARAM_PUBLIC) == null ? false : request.getParameter(PARAM_PUBLIC).equals("on") ? true : false;
        product.setCommon(isPublic);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            product.setUser(user);
            product.setDeleted(false);
            productService.updateProduct(product);
            logger.info("edit product with id = " + product.getId());
        }
        //return ADMIN_MEALS_PAGE;
        return "redirect:/admin/products";
    }
}
