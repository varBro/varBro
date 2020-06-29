package com.varbro.varbro.controller;

import com.varbro.varbro.service.MailService;
import com.varbro.varbro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @GetMapping("/test-mail") //test mail
    public String send() {
        mailService.testMail("mcjmode@gmail.com", "test", "test");
        return "sent!";
    }

    /*
    @PostMapping("/password-reset")
    public String sendPasswordResetRequest() {
        userService.requestPasswordReset("...");
        return "sent!";
    }*/
}
