package com.zup.zupbank.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Service
public class MailService {

    private JavaMailSender configMail(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);
        mailSender.setUsername("933b3de814a370");
        mailSender.setPassword("cba98b468d3239");

        return mailSender;
    }

    public void sendEmail(){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("noreply@zupbank.com");
        email.setTo("teste@zupbank.com");
        email.setSubject("Assunto");
        email.setText("Obrigado pelo cadastro");
        JavaMailSender sender = this.configMail();
        sender.send(email);
    }
}
