package com.maha.vehicle_management.Services;

import com.maha.vehicle_management.Entities.Account;
import com.maha.vehicle_management.Models.CustomUserDetails;
import com.maha.vehicle_management.Repositories.AccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

public class CustomDBUserDetailsManager implements UserDetailsManager {

    private final AccountRepository accountRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy
            = SecurityContextHolder.getContextHolderStrategy();
    private final PasswordEncoder passwordEncoder;


    public CustomDBUserDetailsManager(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserDetails user) {
        CustomUserDetails userDetails = (CustomUserDetails) user;
        Account tmp = userDetails.getAccount();
        tmp.setPassword(passwordEncoder.encode(tmp.getPassword()));
        accountRepository.save(tmp);
    }

    @Override
    public void updateUser(UserDetails user) {
        CustomUserDetails userDetails = (CustomUserDetails) user;
        Account tmp = userDetails.getAccount();
        tmp.setPassword(passwordEncoder.encode(tmp.getPassword()));
        accountRepository.save(tmp);
    }

    @Override
    public void deleteUser(String username) {
        Account account = accountRepository.findOneByUsername(username);
        account.setEnabled(false);
        accountRepository.delete(account);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = this.securityContextHolderStrategy.getContext().getAuthentication();
        String username = currentUser.getName();
        Account account = accountRepository.findOneByUsername(username);
        if (account.getPassword().equals(oldPassword)) {
            account.setPassword(newPassword);
            accountRepository.save(account);
        }
    }

    @Override
    public boolean userExists(String username) {
        List<Account> accounts = accountRepository.findByUsername(username);
        return accounts.size() == 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findOneByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(account);
    }
}
