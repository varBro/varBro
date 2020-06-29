package com.varbro.varbro.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "password_reset_tokens")
public class PasswordResetToken implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }
}
