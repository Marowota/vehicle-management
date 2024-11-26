package com.maha.vehicle_management.Security;

import com.maha.vehicle_management.Entities.Redis.ApiKey;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {
    private final ApiKey apiKey;

    public ApiKeyAuthenticationToken(ApiKey apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return apiKey.getKey();
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}