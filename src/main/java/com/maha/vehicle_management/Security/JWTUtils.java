package com.maha.vehicle_management.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maha.vehicle_management.DTO.AccountDTO;
import com.maha.vehicle_management.Entities.Account;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.Date;

@Deprecated
public class JWTUtils {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_PREFIX = "Bearer_";
    private static final int SIX_HOURS_MILLISECOND = 1000 * 60 * 60 * 6;
    private static final int SIX_HOURS = 3600 * 6;

    private static final String USER_CLAIM = "user_claim";
    private static final String ISSUER = "auth0";

    @Value("${jwt-key}")
    private static String SECRET_KEY = "SaltIsCute";

    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public static String createToken(AccountDTO accountInfo)
            throws JsonProcessingException {
        var builder = JWT.create();
        var userTokenJson = JSON_MAPPER.writeValueAsString(accountInfo);
        builder.withClaim(USER_CLAIM, userTokenJson);
        return builder.withIssuedAt(new Date())
                .withIssuer(ISSUER)
                .withExpiresAt(new Date(System.currentTimeMillis() + SIX_HOURS_MILLISECOND))
                .sign(ALGORITHM);
    }

    public static void setJwtToClient(AccountDTO accountInfo)
            throws JsonProcessingException {
        var token = createToken(accountInfo);
        var cookie = new Cookie(AUTHORIZATION_HEADER, AUTHORIZATION_PREFIX + token);
        cookie.setMaxAge(SIX_HOURS);
        cookie.setPath("/");
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getResponse().addCookie(cookie);
    }

    public static DecodedJWT verifyToken(String token) {
        var verifier = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }

    public static AccountDTO getAccountInfo(DecodedJWT decodedJWT)
            throws JsonProcessingException {
        var userTokenJson = decodedJWT.getClaim(USER_CLAIM).asString();
        return JSON_MAPPER.readValue(userTokenJson, AccountDTO.class);
    }

    public static String getToken(HttpServletRequest request) {
        var cookies = request.getCookies();
        var authCookie = Arrays.stream(cookies)
                .filter(e -> e.getName().equals(AUTHORIZATION_HEADER))
                .findFirst()
                .orElseThrow();
        String authorizationHeader = authCookie.getValue();
        Assert.isTrue(authorizationHeader
                .startsWith(AUTHORIZATION_PREFIX), "Authorization header must start with '"
                + AUTHORIZATION_PREFIX + "'.");

        String jwtToken = authorizationHeader.substring(AUTHORIZATION_PREFIX.length());
        return jwtToken;
    }

    public static AccountDTO getSession() throws AccessDeniedException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new AccessDeniedException("Unauthorized");
        }
        return (AccountDTO) authentication.getPrincipal();
    }

}
