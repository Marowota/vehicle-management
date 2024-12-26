package com.maha.vehicle_management.Controllers;

import com.maha.vehicle_management.Entities.Account;
import com.maha.vehicle_management.Models.CustomUserDetails;
import com.maha.vehicle_management.Models.enums.AccountRequestResult;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userDetailsManager;

    public AccountController(UserDetailsManager userDetailsManager, ModelMapper modelMapper,
                             PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/get")
    public ResponseEntity<Account> getAccountByUsername(@RequestParam String username) {
        Account account = ((CustomUserDetails)userDetailsManager.loadUserByUsername(username)).getAccount();
        account.setPassword("");
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        if (userDetailsManager.userExists(account.getUsername())) {
            return new ResponseEntity<>("User already exist",HttpStatus.BAD_REQUEST);
        }
        CustomUserDetails userDetails = new CustomUserDetails(account);
        userDetailsManager.createUser(userDetails);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AccountRequestResult> updateAccount(@RequestBody Account account) {
        CustomUserDetails userDetails = new CustomUserDetails(account);
        userDetailsManager.updateUser(userDetails);
        return new ResponseEntity<>(AccountRequestResult.UPDATED, HttpStatus.OK);
    }

    @GetMapping("/get-hashed")
    public String getSalt(@RequestParam("psw") String password) {
        return passwordEncoder.encode(password);
    }


    @GetMapping("/verify")
    public String getVerify() {
        return "OK";
    }


}

