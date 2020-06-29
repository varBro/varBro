package com.varbro.varbro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {

    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (request.isUserInRole("ROLE_FINANCE")) {
            return "redirect:/finance";
        }
        else if (request.isUserInRole("ROLE_LOGISTICS")) {
            return "redirect:/logistics";
        }
        else if (request.isUserInRole("ROLE_HR")) {
            return "redirect:/hr";
        }
        else if (request.isUserInRole("ROLE_PRODUCTION")) {
            return "redirect:/production";
        }
        return "redirect:/";
    }

}
