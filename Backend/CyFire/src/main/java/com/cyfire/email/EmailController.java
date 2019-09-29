package com.cyfire.email;

import com.cyfire.users.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.Properties;

/**
 * Sends an email to a given user.
 *
 * @author Steven Marshall Sheets
 */
@RestController
@RequestMapping("/send_code")
public class EmailController {
    static {
        System.out.println("\nCyFire: Ready to send verification code to Net-ID...");
    }

    private EmailConfiguration emailConfiguration;

    public EmailController(EmailConfiguration emailConfiguration) {
        this.emailConfiguration = emailConfiguration;
    }

    @PostMapping // Give this a return type so it's easier to see if it worked
    public void sendFeedback(@RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback is not valid");
        }

        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        setUpMailSender(mailSender);

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        sendMessage(mailMessage, mailSender, user);
    }

    private void setUpMailSender(JavaMailSenderImpl mailSender) {
        mailSender.setHost(this.emailConfiguration.getHost());
        mailSender.setPort(this.emailConfiguration.getPort());
        mailSender.setUsername(this.emailConfiguration.getUsername());
        mailSender.setPassword(this.emailConfiguration.getPassword());
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
    }

    private void sendMessage(SimpleMailMessage mailMessage, JavaMailSenderImpl mailSender, User user) {
        mailMessage.setFrom("CyFireAdm1n@Gmail.com");

        mailMessage.setTo(user.getNetID()); // user.getNet_id() // We are using the admin account so
        // we don't annoy people with emails, for the moment.

        mailMessage.setSubject("CyFire User Test...");

        mailMessage.setText("Hi there! Welcome to your new flame, CyFire.\nWe hope you have a great "
                + "experience.\nPlease enter the code below into the app to begin finding your CyMate!\n"
                + user.getCode());

        System.out.println(mailMessage);

        // Send mail
        mailSender.send(mailMessage);
    }

}
