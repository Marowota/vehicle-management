package com.maha.vehicle_management.Config;

import com.maha.vehicle_management.Config.properties.CustomSecurityProperty;
import com.maha.vehicle_management.Repositories.AccountRepository;
import com.maha.vehicle_management.Security.APIKeyAuthenticationFilter;
import com.maha.vehicle_management.Services.CustomDBUserDetailsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.security.SecureRandom;

@Configuration
public class SecurityConfig {

    @Bean
    UserDetailsManager userDetailsService(AccountRepository accountRepository) {
        return new CustomDBUserDetailsManager(accountRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder(CustomSecurityProperty property) {
        return new BCryptPasswordEncoder(5,
                new SecureRandom(property.getSeed().getBytes()));
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            APIKeyAuthenticationFilter customFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login", "/account/get-hashed").permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterAt(customFilter, BasicAuthenticationFilter.class);
        return http.build();
    }


}
