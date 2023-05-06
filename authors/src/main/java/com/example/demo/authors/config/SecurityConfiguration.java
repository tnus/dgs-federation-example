package com.example.demo.authors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().antMatchers("/graphql").permitAll();
//        http.authorizeRequests().antMatchers("/actuator").permitAll();
        http.csrf().disable();
        return http.build();
    }

}
