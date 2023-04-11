package com.intern.hrmanagementapi.config;

import com.intern.hrmanagementapi.constant.EndpointConst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  private final String[] AUTH_WHITE_LIST = {"/api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**","/api/v1/users/**","/api/v1/employee/**","/api/v1/departments/**"};

  @Autowired
  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers(AUTH_WHITE_LIST)
        .permitAll().anyRequest().authenticated();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    http.logout().logoutUrl(EndpointConst.LOGOUT).addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((req, res, authentication) -> SecurityContextHolder.clearContext());
    return http.build();
  }

  //  @Bean
  //  public WebSecurityCustomizer webSecurityCustomizer() {
  //    return (web) -> web.ignoring().requestMatchers(AUTH_WHITE_LIST);
  //  }
}
