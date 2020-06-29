package com.varbro.varbro.service;

import com.varbro.varbro.model.PasswordResetToken;
import com.varbro.varbro.model.User;
import com.varbro.varbro.repository.PasswordResetTokenRepository;
import com.varbro.varbro.repository.UserRepository;
import com.varbro.varbro.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    public void saveUser(User user) {

        userRepository.save(user);
    }

    public void saveUsers(List<User> users) {

        userRepository.saveAll(users);
    }

    public Iterable<User> getUsers() {

        return userRepository.findAll();
    }

    public Iterable<User> getUsersOrderedBySurname() {
        return userRepository.findAllByOrderBySurname();
    }

    public Iterable<User> getUsersLikeSurname(String surname) {
        return userRepository.findBySurnameContainingIgnoreCase(surname);
    }

    public Iterable<User> getUsersLikeName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public Iterable<User> getUsersLikeNameOrLikeSurname(String name, String surname) {
        return userRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(name, surname);
    }

    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public void delete(User user) {

        userRepository.delete(user);
    }

    public void deleteAll() {

        userRepository.deleteAll();
    }

    public User getOne(Long id) {

        return userRepository.getOne(id);
    }

    public boolean requestPasswordReset(String email) {
        boolean returnValue = false;

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return returnValue;
        }

        String id = String.valueOf(user.getId());
        String token = Utils.generatePasswordResetToken(id);

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUserDetails(user);
        passwordResetTokenRepository.save(passwordResetToken);

        MailService mailService = new MailService();
        mailService.sendPasswordResetRequest(user.getEmail(), token,true);
        returnValue = true;
        return returnValue;
    }

    public String changeStatus(String email) {

        try {
            User user = getUserByEmail(email);
            int status = user.getStatus();

            if (status > 1) {
                user.setStatus(status-1);
                saveUser(user);
                return "0";
            }
            else if (status == 1) {
                user.setStatus(status-1);
                saveUser(user);
                return "Account locked";
            } else if (status == 0) {
                return "Account locked";
            }
        } catch (Exception e) {
            return "No user found";
        }
        return "0";
    }

    public void resetStatus(String email) {
        User user = getUserByEmail(email);
        user.setStatus(3);
        saveUser(user);
    }
}