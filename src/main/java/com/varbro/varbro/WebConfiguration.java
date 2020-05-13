package com.varbro.varbro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebConfiguration extends WebSecurityConfigurerAdapter {
    @Qualifier("authUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;
    
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .and().inMemoryAuthentication()
                .withUser("user").password(getPasswordEncoder().encode("user1")).roles("USER")
                .and().withUser("admin").password(getPasswordEncoder().encode("admin1")).roles("USER","ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").hasRole("USER")
                .and().authorizeRequests().anyRequest().hasRole("ADMIN")
                .and().formLogin().permitAll()
                .and().logout().permitAll();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
}