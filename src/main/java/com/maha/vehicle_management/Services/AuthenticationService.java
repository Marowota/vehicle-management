package com.maha.vehicle_management.Services;

import com.maha.vehicle_management.DTO.AccountRegistrationDTO;
import com.maha.vehicle_management.Entities.Account;
import com.maha.vehicle_management.Models.enums.AccountRegistrationResult;
import com.maha.vehicle_management.Repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
//    private final AccountRepository accountRepository;
//
//    public AuthenticationService(AccountRepository accountRepository) {
//        this.accountRepository = accountRepository;
//    }
//
//    public AccountRegistrationDTO register(String username, String password) {
//        AccountRegistrationDTO accountRegistrationDTO = new AccountRegistrationDTO();
//        Account existed = accountRepository.findOneByUsername(username);
//        if (existed != null) {
//            accountRegistrationDTO.setResult(AccountRegistrationResult.USERNAME_EXISTS);
//            return accountRegistrationDTO;
//        }
//        Account account = new Account(username, password);
//        accountRepository.save(account);
//        return accountRegistrationDTO;
//    }
//
//    public void login(String username, String password){
//        Account account = accountRepository.findOneByUsername(username);
//
//    }
}
