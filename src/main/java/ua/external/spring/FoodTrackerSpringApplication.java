package ua.external.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import ua.external.spring.entity.*;
import ua.external.spring.filter.XSSFilter;
import ua.external.spring.repository.UserRepository;
import ua.external.spring.service.*;
import ua.external.spring.service.impl.*;

import java.util.Locale;

@SpringBootApplication
public class FoodTrackerSpringApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(FoodTrackerSpringApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean xssPreventFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new XSSFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    /*@Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".html");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }*/



    @Bean
    public CommandLineRunner addGender(final GenderService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                service.create(new Gender("Female"));
                service.create(new Gender("Male"));
            }
        };
    }

    @Bean
    public CommandLineRunner addRole(final UserRoleService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                service.create(new UserRole("USER"));
                service.create(new UserRole("ADMIN"));
            }
        };
    }

    @Bean
    public CommandLineRunner addActivity(final ActivityService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                service.create(new Activity("High", 1.9));
                service.create(new Activity("Medium", 1.5));
                service.create(new Activity("Low", 1.2));
            }
        };
    }

    @Bean
    public CommandLineRunner addNutrGoal(final NutritionGoalService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                service.create(new NutritionGoal("Weight Lost", 0.9));
                service.create(new NutritionGoal("Weight Maintenance", 1.0));
                service.create(new NutritionGoal("Weight Gain", 1.1));
            }
        };
    }

    @Bean
    public CommandLineRunner addEatPeriod(final EatPeriodService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                service.create(new EatPeriod("Breakfast"));
                service.create(new EatPeriod("Lunch"));
                service.create(new EatPeriod("Snack"));
                service.create(new EatPeriod("Supper"));
            }
        };
    }

    @Bean
    public CommandLineRunner addAdmin(final UserService userService, final UserRoleService userRoleService, final UserRepository userRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                User user = User.builder()
                        .login("test.user@gmail.com")
                        .role(userRoleService.findUserRoleByName("ADMIN").get())
                        .password(passwordEncoder.encode("Qwe12345"))
                        .build();
                userRepository.save(user);
            }
        };
    }

}
