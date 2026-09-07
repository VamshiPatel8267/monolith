package com.monolithic.FoodOrdering.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class OrderNotification {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOrderEmail(String To, String Subject , String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(To);
        mailMessage.setSubject(Subject);
        mailMessage.setText(text);
        mailSender.send(mailMessage);
    }
}
