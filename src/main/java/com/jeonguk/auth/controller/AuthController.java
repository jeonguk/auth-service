package com.jeonguk.auth.controller;

import com.jeonguk.auth.dto.LoginDTO;
import com.jeonguk.auth.security.JWTReactiveAuthenticationManager;
import com.jeonguk.auth.security.JWTToken;
import com.jeonguk.auth.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.Validator;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/authorize")
public class AuthController {

    private final TokenProvider tokenProvider;
    private final JWTReactiveAuthenticationManager authenticationManager;
    private final Validator validation;

    @PostMapping
    public Mono<JWTToken> authorize(@Valid @RequestBody LoginDTO loginDTO) {
        if (!this.validation.validate(loginDTO).isEmpty()) {
            return Mono.error(new RuntimeException("Bad request"));
        }

        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        Mono<Authentication> authentication = this.authenticationManager.authenticate(authenticationToken);
        authentication.doOnError(throwable -> {
            throw new BadCredentialsException("Bad crendentials");
        });
        ReactiveSecurityContextHolder.withAuthentication(authenticationToken);

        return authentication.map(auth -> {
            String jwt = tokenProvider.createToken(auth);
            return new JWTToken(jwt);
        });
    }
}
