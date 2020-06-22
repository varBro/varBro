package com.varbro.varbro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public void MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String to, String subject, String text, boolean isHtmlContent) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, isHtmlContent);
        javaMailSender.send(mimeMessage);
    }

    public void sendPasswordResetRequest(String to, String token, boolean isHtmlContent) throws MessagingException {
        final String subject = "Password reset";
        final String passwordResetBody = "<a href='http://192.168.99.100:8080?token=" + token + "'</a><br/>";
        try {
            sendMail(to, subject, passwordResetBody, isHtmlContent);
        }
        catch (MessagingException mex){
            mex.printStackTrace();
        }
    }


}
