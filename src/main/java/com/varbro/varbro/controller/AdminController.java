package com.varbro.varbro.controller;

import com.varbro.varbro.model.User;
import com.varbro.varbro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin")
    public String adminIndex(){
        return "admin/index";
    }

    @GetMapping("/admin/add-user")
    public String adminAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/add-user";
    }

    @PostMapping("/admin/add-user")
    public ModelAndView adminAddUserSubmit(@ModelAttribute User user) {
        user.setPassword("abc");
        userRepository.save(user);
        return new ModelAndView("redirect:/user/" + user.getId());
    }

    @GetMapping("/admin/users")
    public String adminUsers(){
        return "/login";
    }

}
