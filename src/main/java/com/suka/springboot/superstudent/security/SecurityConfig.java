package com.suka.springboot.superstudent.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails peppa = User.builder()
                .username("peppa")
                .password("{noop}peppa123")
                .roles("STUDENT")
                .build();

        UserDetails mommy = User.builder()
                .username("mommy")
                .password("{noop}mommy123")
                .roles("STUDENT", "TEACHER")
                .build();

        UserDetails daddy = User.builder()
                .username("daddy")
                .password("{noop}daddy123")
                .roles("STUDENT", "TEACHER", "PRINCIPLE")
                .build();

        return new InMemoryUserDetailsManager(peppa, mommy, daddy);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/students").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/students/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/students").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/students/**").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("PRINCIPLE")
        );

        // use basic HTTP authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Reference Forgery
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
