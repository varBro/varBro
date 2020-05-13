package com.varbro.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s.equals("error")) {
            User user = new User("", "",
                    PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("user1"),
                    "error", "", 0);
            return new AuthUserDetails(user);
        } else if (s.equals("a")) {
            User user = new User("", "",
                    PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("admin1"),
                    "admin", "", 0);
            return new AuthUserDetails(user);
        }
        throw new UsernameNotFoundException("not found user " + s);
    }
}
