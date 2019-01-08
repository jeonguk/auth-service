package com.jeonguk.auth.security;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTToken {
    private String token;
}