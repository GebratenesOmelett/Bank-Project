package com.example.bankbackend.config;

import com.example.bankbackend.customer.CustomerUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityFilterConfig {
    private CustomerUserDetailService customerUserDetailService;
    public SecurityFilterConfig(CustomerUserDetailService customerUserDetailService) {
        this.customerUserDetailService = customerUserDetailService;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/api/transfers/**").hasRole("USER")
                        .requestMatchers("/api/customers").permitAll()
                        .requestMatchers("/api/customers/**").hasRole("USER")
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(customerUserDetailService)
                .csrf().disable();
        return httpSecurity.build();
        //hello linux how are you
    }
}
