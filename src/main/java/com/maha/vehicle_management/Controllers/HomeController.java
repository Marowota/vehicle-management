package com.maha.vehicle_management.Controllers;

import com.maha.vehicle_management.DTO.LoginDTO;
import com.maha.vehicle_management.Entities.Redis.ApiKey;
import com.maha.vehicle_management.Security.ApiKeyAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final ApiKeyAuthenticationService apiKeyAuthenticationService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userDetailsManager;

    public HomeController(ApiKeyAuthenticationService apiKeyAuthenticationService, PasswordEncoder passwordEncoder
                            ,UserDetailsManager userDetailsManager) {
        this.apiKeyAuthenticationService = apiKeyAuthenticationService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO userDTO) {
        UserDetails user;
        System.out.println(userDTO.getUsername());
        try{
            user = userDetailsManager.loadUserByUsername(userDTO.getUsername());
        }
        catch (Exception e){
            return new ResponseEntity<String>("Wrong username or password", HttpStatus.UNAUTHORIZED);
        }
        if (user != null && !passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return new ResponseEntity<String>("Wrong username or password", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<ApiKey>(apiKeyAuthenticationService.getNewKey(userDTO.getUsername()), HttpStatus.OK);
    }
}
