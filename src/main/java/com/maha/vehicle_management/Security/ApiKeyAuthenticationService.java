package com.maha.vehicle_management.Security;

import com.maha.vehicle_management.Entities.Account;
import com.maha.vehicle_management.Entities.Redis.ApiKey;
import com.maha.vehicle_management.Repositories.AccountRepository;
import com.maha.vehicle_management.Repositories.Redis.ApiKeyRepository;
import com.maha.vehicle_management.Repositories.VehicleRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApiKeyAuthenticationService {
    private final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private final ApiKeyRepository apiKeyRepository;
    private final PasswordEncoder encoder;
    private final AccountRepository accountRepository;

    private ApiKeyAuthenticationService(ApiKeyRepository apiKeyRepository, PasswordEncoder passwordEncoder,
                                        AccountRepository accountRepository) {
        this.apiKeyRepository = apiKeyRepository;
        encoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    public void authenticate(HttpServletRequest request) {
        String providedKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        final ApiKey apiKey = apiKeyRepository.findByKey(providedKey);
        if (!apiKey.getKey().equals(providedKey)) {
            throw new BadCredentialsException("Invalid API Key");
        }
        Authentication authentication = new ApiKeyAuthenticationToken(apiKey, AuthorityUtils.NO_AUTHORITIES);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public ApiKey getNewKey(String username) {
        Account account = accountRepository.findOneByUsername(username);
        apiKeyRepository.removeByUid(account.getId().toString());
        ApiKey apiKey = new ApiKey();

        int noLoop = 0;
        do {
            apiKey.setKey(encoder
                    .encode(LocalDateTime.now().toString()
                            + username));
            noLoop++;
        } while (apiKeyRepository.findByKey(apiKey.getKey()) != null);
        System.out.println("Looped:" + noLoop);
        apiKey.setUid(account.getId().toString());
        apiKeyRepository.save(apiKey);
        return apiKey;
    }

}
