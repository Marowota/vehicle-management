package com.maha.vehicle_management.Controllers;

import com.maha.vehicle_management.Entities.Account;
import com.maha.vehicle_management.Models.CustomUserDetails;
import com.maha.vehicle_management.Models.enums.AccountRequestResult;
import com.maha.vehicle_management.Repositories.AccountRepository;
import com.maha.vehicle_management.Repositories.Redis.ApiKeyRepository;
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
    private final ApiKeyRepository apiKeyRepository;
    private final AccountRepository accountRepository;

    public AccountController(UserDetailsManager userDetailsManager, ModelMapper modelMapper,
                             PasswordEncoder passwordEncoder, ApiKeyRepository apiKeyRepository, AccountRepository accountRepository) {
        this.userDetailsManager = userDetailsManager;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.apiKeyRepository = apiKeyRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/get")
    public ResponseEntity<Account> getAccountByKey(@RequestHeader("X-API-KEY") String key) {
        String uid = apiKeyRepository.findByKey(key).getUid();
        Account account = accountRepository.findOneById(Long.parseLong(uid));
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
        System.out.println(account.getUsername());
        System.out.println(account.getPassword());
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

