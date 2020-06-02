package com.varbro.varbro.service;

import com.varbro.varbro.model.PasswordResetToken;
import com.varbro.varbro.model.User;
import com.varbro.varbro.repository.PasswordResetTokenRepository;
import com.varbro.varbro.repository.UserRepository;
import com.varbro.varbro.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if(user == null) {
            return returnValue;
        }
        String id = String.valueOf(user.getId());
        String token = Utils.generatePasswordResetToken(id);

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUserDetails(user);
        passwordResetTokenRepository.save(passwordResetToken);

        //MailService mailService;
        //mailService.sendPasswordResetRequest(user.getEmail(), token,true);

        return returnValue;
    }
}