package com.thinhlh.mi_learning_backend.config;

import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import com.thinhlh.mi_learning_backend.config.filter.CustomAuthenticationFilter;
import com.thinhlh.mi_learning_backend.config.filter.CustomAuthorizationFilter;
import com.thinhlh.mi_learning_backend.config.handler.CustomAccessDeniedHandler;
import com.thinhlh.mi_learning_backend.helper.SecurityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder bcryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bcryptPasswordEncoder);
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable().httpBasic();

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        addAuthorizationPaths(http);

        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Find requests that need to be authorized
    private void addAuthorizationPaths(HttpSecurity http) throws Exception {
        var authorizeRequests = http.authorizeRequests();

        SecurityHelper.REQUEST_AUTHORIZATION_PATH.forEach((pathAndMethods, authorities) -> {
            if (authorities.isEmpty()) {
                // Permit all
                pathAndMethods.getSecond().forEach(method ->
                        authorizeRequests
                                .antMatchers(method, pathAndMethods.getFirst())
                                .permitAll()
                );
            } else {
                var path = pathAndMethods.getFirst();
                pathAndMethods.getSecond().forEach(method -> {
                    authorizeRequests.antMatchers(method, path).hasAnyAuthority(
                            (authorities.get().stream().map(Role.RoleName::name)).toArray(String[]::new)
                    );
                });
            }
        });
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        var source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        firewall.setAllowBackSlash(true);
        return firewall;
    }
}
