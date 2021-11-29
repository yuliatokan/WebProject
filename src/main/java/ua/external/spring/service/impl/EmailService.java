package ua.external.spring.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.external.spring.service.IEmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender sender;

    final static Logger logger = LogManager.getLogger();

    @Override
    public void send(String sendTo, String subject, String messageToSend) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            message.setContent(messageToSend, "text/html");
            helper.setTo(sendTo);
            helper.setSubject(subject);
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("Error sending email!", e);
        }
    }

    @Override
    public void sendWelcomeLetter(String sendTo) {
        final String WELCOME_LETTER = getWelcomeContent();
        send(sendTo, "Welcome to Eat&Fit", WELCOME_LETTER);
    }

    @Override
    public void sendWarningLetter(String sendTo) {
        final String WARNING_LETTER = getWarningContent();
        send(sendTo, "Your limit reached", WARNING_LETTER);
    }

    private String getWelcomeContent(){
        return readFromFile("src/main/webapp/static/email/welcome_letter.html");
    }

    private String getWarningContent(){
        return readFromFile("src/main/webapp/static/email/warning_letter.html");
    }

    private String readFromFile(String path){
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
            logger.log(Level.FATAL, "Reading file error", e);
        }
        String content = contentBuilder.toString();
        return content;
    }
}
