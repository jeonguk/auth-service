package com.jeonguk.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/hello/{name}")
    public Mono<String> hello(@PathVariable String name) {
        return Mono.just("Hello " + name);
    }
}
