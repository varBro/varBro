package com.varbro.varbro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class  SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomLoginSuccessHandler successHandler;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/user/**").hasAuthority("EMPLOYEE")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/finance/**").hasAnyAuthority("ROLE_PRODUCTION", "ROLE_ADMIN")
                .antMatchers("/production/request/add").not().hasAuthority("ROLE_LOGISTICS")
                .antMatchers("/production/request/*").hasAnyAuthority("ROLE_LOGISTICS", "ROLE_PRODUCTION", "ROLE_ADMIN")
                .antMatchers("/production/request/**").hasAuthority("ROLE_LOGISTICS")
                .antMatchers("/production/*").hasAnyAuthority("ROLE_PRODUCTION", "ROLE_ADMIN")
                .antMatchers("/logistics/manager/**", "/logistics/order/*/**").not().access("hasAuthority('ROLE_LOGISTICS') and not hasAuthority('MANAGER')")
                .antMatchers("/logistics/manager/**", "/logistics/order/*/**").access("hasAuthority('ROLE_LOGISTICS') and hasAuthority('MANAGER')")
                .antMatchers("/logistics/**").hasAnyAuthority("ROLE_LOGISTICS", "ROLE_ADMIN")
                .antMatchers("/hr/**").hasAnyAuthority("ROLE_HR", "ROLE_ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler).permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout");

        http.authorizeRequests()
                .antMatchers("/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**",
                        "/login**")
                .permitAll()
                .anyRequest()
                .authenticated();

        http
                .csrf()
                .disable();
    }

}
