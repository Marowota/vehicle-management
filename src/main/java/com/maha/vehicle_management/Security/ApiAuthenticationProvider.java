package com.maha.vehicle_management.Security;

import com.maha.vehicle_management.Entities.Redis.ApiKey;
import com.maha.vehicle_management.Repositories.Redis.ApiKeyRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

@Deprecated
public class ApiAuthenticationProvider implements AuthenticationProvider {

    private final ApiKeyRepository repository;
    private final PasswordEncoder encoder;

    ApiAuthenticationProvider(ApiKeyRepository apiKeyRepository, PasswordEncoder passwordEncoder) {
        this.repository = apiKeyRepository;
        this.encoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKey = authentication.getCredentials().toString();
        final ApiKey apiKeyObject = repository.findByKey(apiKey);

        if (apiKey == null || !encoder.matches(apiKey, apiKeyObject.getKey())) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthenticationToken(apiKeyObject, AuthorityUtils.NO_AUTHORITIES);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(ApiKeyAuthenticationToken.class);
    }
}
