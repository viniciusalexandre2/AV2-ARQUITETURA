package com.example.loja.service;

import com.example.loja.model.Usuario;
import com.example.loja.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repo.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        var authorities = u.getRoles().stream()
                           .map(SimpleGrantedAuthority::new)
                           .collect(Collectors.toList());
        return new User(u.getUsername(), u.getPassword(), authorities);
    }
}