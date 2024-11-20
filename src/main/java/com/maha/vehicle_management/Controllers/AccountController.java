package com.maha.vehicle_management.Controllers;

import com.maha.vehicle_management.DTO.AccountDTO;
import com.maha.vehicle_management.DTO.AccountRegistrationDTO;
import com.maha.vehicle_management.Entities.Account;
import com.maha.vehicle_management.Models.CustomUserDetails;
import com.maha.vehicle_management.Services.CustomDBUserDetailsManager;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {

    private final ModelMapper modelMapper;
    UserDetailsManager userDetailsManager;
    public AccountController(UserDetailsManager userDetailsManager, ModelMapper modelMapper) {
        this.userDetailsManager = userDetailsManager;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public String createAccount(@RequestBody Account account) {
        CustomUserDetails userDetails = new CustomUserDetails(account);
        userDetailsManager.createUser(userDetails);
        return "Account created";
    }




}

