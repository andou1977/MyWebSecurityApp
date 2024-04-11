package com.example.mywebsecurityapp;

import com.example.mywebsecurityapp.entity.RoleEntity;
import com.example.mywebsecurityapp.entity.UserEntity;
import com.example.mywebsecurityapp.security.UserInterfaceimpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class MyWebSecurityAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(MyWebSecurityAppApplication.class, args);

    }



}
