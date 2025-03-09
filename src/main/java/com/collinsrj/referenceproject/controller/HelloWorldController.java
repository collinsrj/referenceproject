package com.collinsrj.referenceproject.controller;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * REST controller that provides a simple greeting endpoint.
 *
 * <p>This controller demonstrates basic Spring WebFlux functionality with security integration. It
 * provides a simple endpoint that returns a personalized greeting using the authenticated user's
 * name.
 *
 * @author collinsrj
 * @version 0.0.1-SNAPSHOT
 * @since 1.0
 */
@RestController
@RequestMapping("/api")
public class HelloWorldController {

  /**
   * Endpoint that returns a personalized greeting for the authenticated user.
   *
   * <p>This method demonstrates:
   *
   * <ul>
   *   <li>Reactive security context access
   *   <li>Authentication information retrieval
   *   <li>Reactive response handling
   * </ul>
   *
   * @return a Mono containing a greeting string with the authenticated user's name
   */
  @GetMapping("/hello")
  public Mono<String> sayHello() {
    return ReactiveSecurityContextHolder.getContext()
        .map(context -> context.getAuthentication())
        .map(auth -> String.format("Hello %s!", auth.getName()));
  }
}
