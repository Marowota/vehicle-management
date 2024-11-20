package com.maha.vehicle_management.Config;

import com.maha.vehicle_management.Config.properties.CustomSecurityProperty;
import com.maha.vehicle_management.Repositories.AccountRepository;
import com.maha.vehicle_management.Services.CustomDBUserDetailsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
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
                new SecureRandom(property.getSalt().getBytes()));
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/account/login").permitAll()
                            .anyRequest().authenticated();
                })
                .httpBasic(httpBasic -> {});
        return http.build();
    }


}
