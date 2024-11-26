package com.maha.vehicle_management.Security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class APIKeyAuthenticationFilter implements Filter {

    ApiKeyAuthenticationService apiKeyAuthenticationService;

    public APIKeyAuthenticationFilter(ApiKeyAuthenticationService apiKeyAuthenticationService) {
        this.apiKeyAuthenticationService = apiKeyAuthenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            apiKeyAuthenticationService.authenticate((HttpServletRequest) request);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            filterChain.doFilter(request, response);
        }

        filterChain.doFilter(request, response);
    }
}
