package com.collinsrj.referenceproject.controller;

import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.junit.jupiter.api.Test;

import com.collinsrj.referenceproject.config.SecurityConfig;

@WebFluxTest(HelloWorldController.class)
@Import(SecurityConfig.class)
class HelloWorldControllerTest {

  @Autowired private WebTestClient webTestClient;

  @Test
  void whenNotAuthenticated_thenUnauthorized() {
    webTestClient.get().uri("/api/hello").exchange().expectStatus().isUnauthorized();
  }

  @Test
  @WithMockUser(username = "testUser")
  void whenAuthenticatedAsUser_thenReturnsGreeting() {
    webTestClient
        .get()
        .uri("/api/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Hello testUser!");
  }

  @Test
  @WithMockUser(
      username = "admin",
      roles = {"ADMIN", "USER"})
  void whenAuthenticatedAsAdmin_thenReturnsGreeting() {
    webTestClient
        .get()
        .uri("/api/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Hello admin!");
  }

  @Test
  void whenBasicAuthWithValidCredentials_thenReturnsGreeting() {
    webTestClient
        .get()
        .uri("/api/hello")
        .headers(headers -> headers.setBasicAuth("user", "password"))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Hello user!");
  }

  @Test
  void whenBasicAuthWithInvalidCredentials_thenUnauthorized() {
    webTestClient
        .get()
        .uri("/api/hello")
        .headers(headers -> headers.setBasicAuth("user", "wrongpassword"))
        .exchange()
        .expectStatus()
        .isUnauthorized();
  }
}
