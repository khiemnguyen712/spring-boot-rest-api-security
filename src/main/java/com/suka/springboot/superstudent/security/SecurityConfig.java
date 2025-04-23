package com.suka.springboot.superstudent.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
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
