package com.collinsrj.referenceproject.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Security configuration class for the application.
 *
 * <p>This class configures Spring Security for the reactive web application, including:
 *
 * <ul>
 *   <li>Web security configuration
 *   <li>Password encoding
 *   <li>User details service
 * </ul>
 *
 * @author collinsrj
 * @version 0.0.1-SNAPSHOT
 * @since 1.0
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

  /**
   * Configures the security filter chain for the application.
   *
   * <p>This method sets up the security configuration including:
   *
   * <ul>
   *   <li>CSRF protection settings
   *   <li>Authorization rules
   *   <li>HTTP basic authentication
   * </ul>
   *
   * @param http the ServerHttpSecurity to configure
   * @return the configured SecurityWebFilterChain
   */
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    http
        // Enable CSRF protection if:
        // 1. Your API is accessed directly from browsers
        // 2. You're using session-based authentication
        // 3. You're using cookies to maintain state
        .csrf(ServerHttpSecurity.CsrfSpec::disable) // Disable CSRF for this example
        .authorizeExchange(
            exchanges ->
                exchanges
                    .pathMatchers("/actuator/**")
                    .permitAll() // Allow
                    // actuator
                    // endpoints
                    .anyExchange()
                    .authenticated())
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }

  /**
   * Creates a password encoder bean for secure password hashing.
   *
   * @return BCryptPasswordEncoder instance for password encoding
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Configures the user details service with in-memory users.
   *
   * <p>Creates two default users:
   *
   * <ul>
   *   <li>A regular user with USER role
   *   <li>An admin user with USER and ADMIN roles
   * </ul>
   *
   * @param passwordEncoder the password encoder to use for encoding passwords
   * @return MapReactiveUserDetailsService configured with default users
   */
  @Bean
  public MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails user =
        User.builder()
            .username("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();

    UserDetails admin =
        User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin"))
            .roles("USER", "ADMIN")
            .build();

    return new MapReactiveUserDetailsService(user, admin);
  }
}
