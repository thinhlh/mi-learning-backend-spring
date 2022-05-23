package com.thinhlh.mi_learning_backend.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinhlh.mi_learning_backend.app.auth.controller.dto.LoginRequest;
import com.thinhlh.mi_learning_backend.app.auth.domain.entity.Tokens;
import com.thinhlh.mi_learning_backend.base.BaseResponse;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import com.thinhlh.mi_learning_backend.helper.SecurityHelper;
import com.thinhlh.mi_learning_backend.helper.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static String claim = "Roles";
    public static int ACCESS_TOKEN_TIMEOUT = 24 * 60 * 60 * 1000; // 1 day
    public static int REFRESH_TOKEN_TIMEOUT = 7 * 24 * 60 * 60 * 1000; // 1 week

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    // This is called whenever a user call login request
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        var loginRequest = getLoginRequest(request);

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    // Handle successful authentication
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        var user = (User) authResult.getPrincipal();

        // One hour expire
        var accessToken = SecurityHelper.generateToken(
                user.getUsername(),
                ACCESS_TOKEN_TIMEOUT,
                ListHelper.mapTo(user.getAuthorities(), GrantedAuthority::getAuthority),
                request.getRequestURL().toString());

        // One day expire
        var refreshToken = SecurityHelper.generateToken(
                user.getUsername(),
                REFRESH_TOKEN_TIMEOUT,
                ListHelper.mapTo(user.getAuthorities(), GrantedAuthority::getAuthority),
                request.getRequestURL().toString()
        );

        var tokensResponse = BaseResponse.success(new Tokens(accessToken, refreshToken));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), tokensResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        new ObjectMapper().writeValue(response.getOutputStream(), BaseResponse.error(failed.getMessage()));
    }

    private LoginRequest getLoginRequest(HttpServletRequest request) {

        return StringHelper.mapRequestBodyToObject(request, LoginRequest.class);
    }
}
