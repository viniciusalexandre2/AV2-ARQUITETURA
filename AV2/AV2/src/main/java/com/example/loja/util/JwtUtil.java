package com.example.loja.util;

import com.example.loja.config.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {
    private final JwtProperties props;
    public JwtUtil(JwtProperties props) {
        this.props = props;
    }
    public String gerarToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + props.getExpirationMs());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, props.getSecret())
                .compact();
    }
    public String extrairUsername(String token) {
        return Jwts.parser()
                .setSigningKey(props.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(props.getSecret()).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}