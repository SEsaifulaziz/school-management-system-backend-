package com.SMS.schoolmanagementsystem.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/enrollements/**").hasAnyRole("STUDENT","TEACHER")
                        .requestMatchers("/api/v1/api/students/").permitAll()
                        .requestMatchers("/admin/**").permitAll()
                )
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }


    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("admin")
                .password(passwordEncoder.encode("pass")).
                roles("ADMIN").
                build();

        return new InMemoryUserDetailsManager(user);
    }
}

