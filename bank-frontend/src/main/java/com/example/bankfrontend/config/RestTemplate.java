package com.example.bankfrontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplate {
    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
