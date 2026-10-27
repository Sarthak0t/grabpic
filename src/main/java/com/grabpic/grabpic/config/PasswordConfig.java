package com.grabpic.grabpic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){ //is the interface that defines password encoding and verification operations.
        return new BCryptPasswordEncoder();  //is the implementation we'll use to hash passwords.
    }
}
