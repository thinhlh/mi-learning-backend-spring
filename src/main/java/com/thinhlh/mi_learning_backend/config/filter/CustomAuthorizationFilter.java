package com.thinhlh.mi_learning_backend.config.filter;

import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import com.thinhlh.mi_learning_backend.exceptions.CustomAuthenticationException;
import com.thinhlh.mi_learning_backend.exceptions.UnknownException;
import com.thinhlh.mi_learning_backend.helper.SecurityHelper;
import lombok.val;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    // Paths that does not require token
    private final Map<String, List<HttpMethod>> ignoreTokenPaths = new HashMap<>();

    /**
     * Retrieve all path that does not require token validation @param List<Role> is empty
     */
    public CustomAuthorizationFilter() {

        SecurityHelper.REQUEST_AUTHORIZATION_PATH.forEach((pathAndMethods, roles) -> {
                    if (roles.isEmpty()) {
                        ignoreTokenPaths.putIfAbsent(
                                pathAndMethods.getFirst(),
                                pathAndMethods.getSecond()
                        );
                    }
                }
        );
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) {
        SecurityHelper.authenticate(request, response, authResult -> {
            // Tell the spring that this is the valid authentication
            SecurityContextHolder.getContext().setAuthentication(authResult);
            try {
                filterChain.doFilter(request, response);
            } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Paths should not filter
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        var ignorePaths = ignoreTokenPaths.entrySet().stream().toList();

        if (ignorePaths.stream().noneMatch(it -> {
            if (it.getKey().endsWith("**"))
                return it.getKey().startsWith(request.getServletPath());
            else
                return it.getKey().equals(request.getServletPath());
        })) return false;


        var allowedMethods = ignoreTokenPaths.get(request.getServletPath());
        // This allows some methods on this path
        return allowedMethods.contains(HttpMethod.valueOf(request.getMethod()));
    }
}
