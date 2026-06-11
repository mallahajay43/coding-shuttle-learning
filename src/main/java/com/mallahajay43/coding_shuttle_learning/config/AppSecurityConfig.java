package com.mallahajay43.coding_shuttle_learning.config;

import org.hibernate.StatelessSession;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(
                        authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers(
                                            "/swagger-ui.html",
                                            "/swagger-ui/**",
                                            "/v3/api-docs/**"
                                    ).hasRole("ADMIN")
                                    .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
//                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(formLogin -> Customizer.withDefaults())
                .build();
    }

    @Bean
    UserDetailsService InMemoUserDetailsService(){
        UserDetails user1 = User.builder()
                .username("User 1")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("user 2")
                .password(passwordEncoder().encode("password2"))
                .roles("ADMIN")
                .build();

        return  new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
