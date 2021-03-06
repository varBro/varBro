package com.varbro.varbro.config;

import com.varbro.varbro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "/login?error=true";

        // Fetch the roles from Authentication object
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String currentUser = authentication.getName();
        userService.resetStatus(currentUser);
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        // check user role and decide the redirect URL
        if (roles.contains("ROLE_ADMIN")) {
            url = "/admin";
        } else if (roles.contains("ROLE_FINANCE")) {
            url = "/finance";
        } else if (roles.contains("ROLE_HR")) {
            url = "/hr";
        } else if (roles.contains("ROLE_LOGISTICS")) {
            url = "/logistics";
        } else if (roles.contains("ROLE_PRODUCTION")) {
            url = "/production";
        } else if (roles.contains("ROLE_DISTRIBUTION")) {
            url = "/distribution";
        } else if (roles.contains("EMPLOYEE")) {
            url = "/user";
        }

        return url;
    }
}
