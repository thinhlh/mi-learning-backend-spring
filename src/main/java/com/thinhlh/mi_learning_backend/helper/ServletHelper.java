package com.thinhlh.mi_learning_backend.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.thinhlh.mi_learning_backend.config.filter.CustomAuthenticationFilter;
import com.thinhlh.mi_learning_backend.exceptions.UnknownException;
import org.springframework.data.util.Pair;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public abstract class ServletHelper {

    /**
     * Retrieve current request, throw Unknown error if unable identify request
     */
    public static HttpServletRequest request() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        } catch (IllegalStateException e) {
            throw new UnknownException(e.getMessage());
        }
    }

    public static HttpServletResponse response() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        } catch (IllegalStateException e) {
            throw new UnknownException(e.getMessage());
        }
    }

    public static Pair<String, List<String>> retrieveUsernameAndRoles(
            String authorizationToken,
            Runnable invalidTokenCallback) {
        return retrieveUsernameAndRoles(null, authorizationToken, invalidTokenCallback);
    }

    /**
     * Retrieve username and roles from current sent token with request
     */
    public static Pair<String, List<String>> retrieveUsernameAndRoles(
            HttpServletRequest request,
            String authorizationToken,
            Runnable invalidTokenCallback) {
        // If token is not null, this is the authentication case (where request is not yet mapped)
        String token;
        if (authorizationToken != null) {
            token = decodeToken(authorizationToken);
        } else {
            token = getRawAuthorizationJWTToken(request);
        }

        if (token == null) {
            invalidTokenCallback.run();
            return Pair.of("", Collections.emptyList());
        }

        JWTVerifier jwtVerifier = JWT.require(SecurityHelper.tokenHashAlgorithms()).build();

        var decodedJWT = jwtVerifier.verify(token);

        var username = decodedJWT.getSubject();

        // Actually in this application, we only use one role for each user
        var roles = decodedJWT.getClaim(CustomAuthenticationFilter.claim).asList(String.class);

        return Pair.of(username, roles);
    }

    public static String getRawAuthorizationJWTToken(HttpServletRequest request) {
        return decodeToken(request.getHeader(AUTHORIZATION));
    }

    private static String decodeToken(String token) {
        if (token == null || token.isBlank()) return null;
        else if (!token.startsWith("Bearer ")) return null;
        else return token.substring("Bearer ".length());
    }
}
