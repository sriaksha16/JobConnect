// EmailService.java
package com.example.jobconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String to, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("JobConnect Registration Successful");
        message.setText("Hello " + name + ",\n\nYour JobConnect account has been successfully created!");
        mailSender.send(message);
    }

    public void sendVerificationEmail(String to, String name, String link) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("JobConnect - Verify Your Email");
        message.setText("Hello " + name + ",\n\nPlease verify your account by clicking the link below:\n" + link);
        mailSender.send(message);
    }
    
    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("JobConnect - Password Reset OTP");
        message.setText("Hello,\n\nYour OTP for resetting your password is: " + otp 
                        + "\n\nThis OTP will expire in 10 minutes.\n\n- JobConnect Team");
        mailSender.send(message);
    }
}
