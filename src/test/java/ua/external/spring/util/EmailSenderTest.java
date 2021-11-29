package ua.external.spring.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.external.spring.service.impl.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSenderTest {
    @Autowired
    EmailService emailService;

    @Test
    public void testSendEmail() {
        String email = "yulia.tokan.11@gmail.com";
        emailService.sendWelcomeLetter(email);
    }
}
