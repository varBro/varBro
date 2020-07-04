package com.varbro.varbro.controller;

import com.varbro.varbro.model.User;
import com.varbro.varbro.service.MailService;
import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.UserService;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    String departmentRole;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/users")
    public String showAll(Model model) {
        model.addAttribute("users", userService.getUsersOrderedBySurname());
        return "user/users";
    }

    @PostMapping("/users")
    public String showAll(@RequestParam(value = "name", required = false) String name, Model model) {
        if (!name.equals("")) {
            model.addAttribute("users", userService.getUsersLikeNameOrLikeSurname(name, name));
        } else
            model.addAttribute("users", userService.getUsersOrderedBySurname());
        model.addAttribute("name", name);
        return "user/users";
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/user/{id}/edit")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/user/{id}/edit")
    public ModelAndView editUser(@PathVariable("id") long id, @ModelAttribute User user)
    {
        return getModelAndView(user);
    }

    private ModelAndView getModelAndView(@ModelAttribute User user) {
        user.setStatus(3);
        departmentRole = user.getDepartment().name();

        user.setRoles(new HashSet(Arrays.asList(roleService.getRoleByName("EMPLOYEE"), roleService.getRoleByName("ROLE_" + departmentRole))));
        if (user.getPosition() != null)
        {
            if (user.getPosition().name().equals("ADMIN"))
                user.addRole(roleService.getRoleByName("ROLE_" + user.getPosition().name()));
            else
                user.addRole(roleService.getRoleByName(user.getPosition().name()));
        }

        userService.saveUser(user);
        return new ModelAndView("redirect:/user/" + user.getId());
    }

    @GetMapping("/user/{id}/delete")
    public ModelAndView deleteUser(@PathVariable("id") long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        userService.delete(user);
        return new ModelAndView("redirect:/user/users");
    }

    @GetMapping("/user/profile")
    public ModelAndView showProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userService.getUserByEmail(name);

        return new ModelAndView("redirect:/user/" + user.getId());
    }

    @GetMapping("/user/add-user")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add-user";
    }

    @PostMapping("/user/add-user")
    public ModelAndView addUserSubmit(@ModelAttribute User user) {
        //user.setPassword("$2y$12$LY7TmjsnBYBu2Y5oIJUZte0r0aU/IiU3e4eppfLg5Gz7lqBGHVYPW"); // encoded brovar
        user.setPassword(bCryptPasswordEncoder.encode(generatePassword(user)));
        //haslo - 3 pierwsze litery imienia + 4 ostatnie cyfry nr pesel + 3 pierwsze litery nazwiska
        return getModelAndView(user);
    }

    @GetMapping("/user/{id}/unlock")
    public ModelAndView unlockUser(@PathVariable("id") long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setStatus(3);
        userService.saveUser(user);
        return new ModelAndView("redirect:/user/" + user.getId());
    }

    @GetMapping("/user/{id}/reset-password")
    public ModelAndView resetPassword(@PathVariable("id") long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        user.setPassword(bCryptPasswordEncoder.encode(generatePassword(user)));
        userService.saveUser(user);
        return new ModelAndView("redirect:/user/" + user.getId() + "?reset=true");
    }

    @GetMapping("/user/{id}/change-password")
    public String changePasswordForm(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "user/change-password";
    }

    @PostMapping("/user/{id}/change-password")
    public ModelAndView changePassword(@PathVariable("id") long id,
                                       @RequestParam("old_password") String oldPassword,
                                       @RequestParam("password") String password,
                                       @RequestParam("confirmed") String confirmedPassword)
    {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        if(bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            if (password.equals(confirmedPassword)) {
                user.setPassword(bCryptPasswordEncoder.encode(password));
                userService.saveUser(user);
                return new ModelAndView("redirect:/user/" + user.getId() + "?changed=true");
            } else {
                return new ModelAndView("redirect:/user/" + user.getId() + "/change-password?failure=true");

            }
        } else {
            return new ModelAndView("redirect:/user/" + user.getId() + "/change-password?unauthorized=true");
        }
    }

    public String generatePassword(User user) {

        return user.getName().substring(0,3)+user.getPesel().substring(7,11)+user.getSurname().substring(0,3);
    }


    public String generatePassayPassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {

            @Override
            public String getErrorCode() {
                return null;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }
}
