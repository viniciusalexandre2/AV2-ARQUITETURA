package com.example.loja.controller;

import com.example.loja.model.Usuario;
import com.example.loja.repository.UsuarioRepository;
import com.example.loja.util.JwtUtil;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final UsuarioRepository userRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {
        if (userRepo.findByUsername(dto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }
        Usuario u = Usuario.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .roles(Set.of("ROLE_USER"))
                .build();
        userRepo.save(u);
        return ResponseEntity.ok("Registrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthDTO dto) {
        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );
            String token = jwtUtil.gerarToken(dto.getUsername());
            return ResponseEntity.ok(new TokenDTO(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        }
    }

    @Getter @AllArgsConstructor
    static class AuthDTO { private String username; private String password; }
    @Getter @AllArgsConstructor
    static class TokenDTO { private String token; }
    @Getter @AllArgsConstructor
    static class RegisterDTO { private String username; private String password; }
}