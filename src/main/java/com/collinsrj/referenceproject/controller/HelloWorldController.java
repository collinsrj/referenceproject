package com.collinsrj.referenceproject.controller;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @GetMapping("/hello")
    public Mono<String> sayHello() {
        return ReactiveSecurityContextHolder.getContext()
            .map(context -> context.getAuthentication())
            .map(auth -> String.format("Hello %s!", auth.getName()));
    }
} 