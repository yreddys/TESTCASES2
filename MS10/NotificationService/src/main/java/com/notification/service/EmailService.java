package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.from}")
    private String fromEmail;

    public void sendEmail(String toEmail, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(text, true);

            javaMailSender.send(message);
            System.out.println("Email sent to " + toEmail + ": " + text);
        } catch (MessagingException e) {
            System.err.println("Error sending email to " + toEmail + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
