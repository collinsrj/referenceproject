package com.collinsrj.referenceproject.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.junit.jupiter.api.Test;

@SpringBootTest
class SecurityConfigTest {

  @Autowired private ReactiveUserDetailsService userDetailsService;

  @Autowired private PasswordEncoder passwordEncoder;

  @Test
  void whenRequestingUserDetails_thenUserIsFound() {
    Mono<org.springframework.security.core.userdetails.UserDetails> userDetailsMono =
        userDetailsService.findByUsername("user");

    StepVerifier.create(userDetailsMono)
        .assertNext(
            userDetails -> {
              assertThat(userDetails.getUsername()).isEqualTo("user");
              assertThat(userDetails.getAuthorities()).hasSize(1);
              assertTrue(
                  userDetails.getAuthorities().stream()
                      .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
            })
        .verifyComplete();
  }

  @Test
  void whenRequestingAdminDetails_thenAdminIsFound() {
    Mono<org.springframework.security.core.userdetails.UserDetails> userDetailsMono =
        userDetailsService.findByUsername("admin");

    StepVerifier.create(userDetailsMono)
        .assertNext(
            userDetails -> {
              assertThat(userDetails.getUsername()).isEqualTo("admin");
              assertThat(userDetails.getAuthorities()).hasSize(2);
              assertTrue(
                  userDetails.getAuthorities().stream()
                      .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
              assertTrue(
                  userDetails.getAuthorities().stream()
                      .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
            })
        .verifyComplete();
  }

  @Test
  void whenEncodingPassword_thenPasswordIsEncoded() {
    String rawPassword = "testPassword";
    String encodedPassword = passwordEncoder.encode(rawPassword);

    assertThat(encodedPassword).isNotEqualTo(rawPassword);
    assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
  }
}
