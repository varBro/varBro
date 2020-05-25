package com.varbro.varbro.controller;

import com.varbro.varbro.model.User;
import com.varbro.varbro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public String index(){
        return "user/index";
    }

    @GetMapping("/users")
    public String showAll(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "user/users";
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") long id, Model model)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/user/{id}/edit")
    public String showEditForm(@PathVariable("id") long id, Model model)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/user/{id}/edit")
    public ModelAndView editUser(@ModelAttribute User user)
    {
        userRepository.save(user);
        return new ModelAndView("redirect:/user/" + user.getId());
    }

    @GetMapping("/user/{id}/delete")
    public ModelAndView deleteUser(@PathVariable("id") long id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        userRepository.delete(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/user/profile")
    public ModelAndView showProfile()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByEmail(name);
        return new ModelAndView("redirect:/user/" + user.getId());
    }
}
