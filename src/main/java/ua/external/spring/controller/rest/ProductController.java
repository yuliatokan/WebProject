package ua.external.spring.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.external.spring.dto.ProductDTO;
import ua.external.spring.entity.User;
import ua.external.spring.service.impl.ProductService;
import ua.external.spring.service.impl.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/get_products")
    public List<ProductDTO> getProducts(HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            return productService.findAllProductsForUser(user.getId());
        }
        return new ArrayList<>();
    }
}
