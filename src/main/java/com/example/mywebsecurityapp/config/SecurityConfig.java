package com.example.mywebsecurityapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.GET;


@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) ->
                auth.requestMatchers("/listrole","/username/{username}").hasRole("User") //role user seulement
                        .requestMatchers("/listuser","/searchbyid/{id}").hasRole("Admin") //role admin seulement
                      .requestMatchers("/saveuser").hasAnyRole("Admin","User") //n'importe role admin ou user
                .requestMatchers("/role").permitAll(). // pas besoin de s'authentifier pour acceder tout le monde a acces
                anyRequest().authenticated()).csrf(csrf -> csrf
                .ignoringRequestMatchers("/**") );;
        http.formLogin();
        return http.build();


    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user= User.builder()
                .username("andou")
                .password(passwordEncoder().encode("pass123"))
                .roles("User")
                .build();

        UserDetails admin= User.builder()
                .username("peter")
                .password(passwordEncoder().encode("pass123"))
                .roles("Admin")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }




}

















