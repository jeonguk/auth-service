package com.jeonguk.auth.controller;

import com.jeonguk.auth.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello/{name}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<String> hello(Authentication authentication, @PathVariable String name) {
        log.info("getCurrentUser getUsername {}", SecurityUtils.getCurrentUser(authentication).getUsername());
        log.info("getCurrentUser getAuthorities {}", SecurityUtils.getCurrentUser(authentication).getAuthorities());
        return Mono.just("Hello " + name);
    }

}
