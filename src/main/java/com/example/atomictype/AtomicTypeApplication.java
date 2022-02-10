package com.example.atomictype;

import com.example.atomictype.Business.Entity.Role;
import com.example.atomictype.Business.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AtomicTypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtomicTypeApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            //userService.saveRole(new Role(null, "ROLE_USER"));
            //userService.addRoleToUser("akzhol", "ROLE_USER");
        };
    }
}
