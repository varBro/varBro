package com.varbro.varbro.controller;

import com.varbro.varbro.model.User;
import com.varbro.varbro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public String index(){
        return "user/index";
    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") long id, Model model)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/users")
    public String showAll(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "user/users";
    }
}
