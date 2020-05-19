package com.varbro.varbro.controller;

import com.varbro.varbro.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminIndex(){
        return "admin/index";
    }

    @GetMapping("/admin/add-user")
    public String adminAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/admin/add-user";
    }

    @PostMapping("/admin/add-user")
    public String adminAddUserSubmit(@ModelAttribute User user) {
        // TODO: push to database
        return "admin/user-submitted";
    }

    @GetMapping("/admin/users")
    public String adminUsers(){
        return "/login";
    }

}
