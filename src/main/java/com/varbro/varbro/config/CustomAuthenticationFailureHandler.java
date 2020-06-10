package com.varbro.varbro.config;

import com.varbro.varbro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UserService userService;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String failure = userService.changeStatus(request.getParameter("email"));
        if (failure.equals("Account locked")) {
            response.sendRedirect("/login?error=account-locked");
        } else {
            request.setAttribute("param", "error");
            response.sendRedirect("/login?error=true");
        }
    }
}