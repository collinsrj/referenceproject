package com.collinsrj.referenceproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @GetMapping("/hello")
    public Mono<String> sayHello() {
        return Mono.just("Hello, World! Welcome to the Reference Project.");
    }
} 