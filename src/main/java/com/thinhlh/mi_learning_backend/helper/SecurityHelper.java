package com.thinhlh.mi_learning_backend.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinhlh.mi_learning_backend.app.auth.domain.entity.Tokens;
import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.config.EnvConfig;
import com.thinhlh.mi_learning_backend.config.filter.CustomAuthenticationFilter;
import com.thinhlh.mi_learning_backend.exceptions.CustomAuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @property REQUEST_AUTHORIZATION_PATH indicate whether a method with path should be accessed with specific role
 * List<Role> specify allowed role to interact with endpoint
 * List<HttpMethod> specify the allowed method to interact with endpoint
 */
public abstract class SecurityHelper {

    // Map<<Path,Methods>,Role> => Paths and methods that only access for roles
    // If roles is null, this path with method will be public access
    public static Map<Pair<String, List<HttpMethod>>, Optional<List<Role.RoleName>>> REQUEST_AUTHORIZATION_PATH = new HashMap<>() {{
        putIfAbsent(Pair.of("/login", List.of(HttpMethod.POST)), Optional.empty());
        putIfAbsent(Pair.of("/register", List.of(HttpMethod.POST)), Optional.empty());
        putIfAbsent(Pair.of("/articles", List.of(HttpMethod.POST, HttpMethod.GET)), Optional.of(List.of(Role.RoleName.student, Role.RoleName.teacher)));
        putIfAbsent(Pair.of("/article", List.of(HttpMethod.POST)), Optional.of(List.of(Role.RoleName.teacher)));
        putIfAbsent(Pair.of("/courses", List.of(HttpMethod.GET)), Optional.of(List.of(Role.RoleName.teacher, Role.RoleName.student)));
        putIfAbsent(Pair.of("/course", List.of(HttpMethod.POST)), Optional.of(List.of(Role.RoleName.teacher)));
        putIfAbsent(Pair.of("/sections", List.of(HttpMethod.GET)), Optional.of(List.of(Role.RoleName.teacher, Role.RoleName.student)));
    }};


    private static final String INVALID_TOKEN = "Invalid token.";

    // Generate JWT token
    private static final String secret = "Mi Learning";

    public static Algorithm tokenHashAlgorithms() {
        return Algorithm.HMAC256(secret);
    }

    public static String generateToken(
            String username,
            Integer timeout,
            List<String> authorities,
            String requestUrl
    ) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(Date.from(Instant.now().plusMillis(timeout)))
                .withIssuer(requestUrl)
                .withClaim(CustomAuthenticationFilter.claim, authorities)
                .sign(tokenHashAlgorithms());
    }

    public static String generateToken(String username, List<String> authorities, Boolean isAccessToken) {
        return generateToken(
                username,
                isAccessToken ? CustomAuthenticationFilter.ACCESS_TOKEN_TIMEOUT : CustomAuthenticationFilter.REFRESH_TOKEN_TIMEOUT, authorities,
                ServletHelper.request().getRequestURL().toString()
        );
    }

    public static void generateErrorResponse(HttpServletResponse response, Exception e) throws IOException {

        var errorResponse = BaseResponse.error(e.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
    }

    /// region authentication


    /*
     * Authenticate when user call api
     * */
    public static void authenticate(
            HttpServletRequest request,
            HttpServletResponse response,
            Consumer<UsernamePasswordAuthenticationToken> onSuccess
    ) {
        try {
            var usernameAndRoles = ServletHelper.retrieveUsernameAndRoles(
                    request,
                    null,
                    () -> {
                        throw new CustomAuthenticationException(INVALID_TOKEN);
                    }
            );

            var username = usernameAndRoles.getFirst();
            var authorities = ListHelper.mapTo(usernameAndRoles.getSecond(), SimpleGrantedAuthority::new);

            var usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

            onSuccess.accept(usernamePasswordAuthentication);
        } catch (Exception e) {
            try {
                generateErrorResponse(response, e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    /**
     * Use this method for authenticate user using refresh token
     * <p>
     * which will execute the callback to the UserService to find the user
     * After retrieved the user this will get the authorities (role) and create new access token
     */
    public Tokens authenticate(HttpServletRequest request, Function<String, Role> findRoleByUsername) {
        try {
            var usernameAndRoles = ServletHelper.retrieveUsernameAndRoles(null, () -> {
                throw new CustomAuthenticationException(INVALID_TOKEN);
            });

            var role = findRoleByUsername.apply(usernameAndRoles.getFirst());

            var currentRequest = ServletHelper.request();

            var accessToken = generateToken(
                    usernameAndRoles.getFirst(),
                    CustomAuthenticationFilter.ACCESS_TOKEN_TIMEOUT,
                    List.of(role.getTitle()),
                    currentRequest.getRequestURL().toString()
            );

            // Current JWT token is refresh token
            return new Tokens(accessToken, ServletHelper.getRawAuthorizationJWTToken(request));
        } catch (Exception e) {
            throw new CustomAuthenticationException(e.getMessage());
        }
    }

    /// endregion
}
