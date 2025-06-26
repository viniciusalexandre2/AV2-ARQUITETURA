package com.example.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.loja.repository.UsuarioRepository;
import com.example.loja.service.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class LojaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LojaApplication.class, args);
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository repo) {
        return new CustomUserDetailsService(repo);
    }
}