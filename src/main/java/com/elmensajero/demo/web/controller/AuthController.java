package com.elmensajero.demo.web.controller;

import com.elmensajero.demo.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // Por ahora usuario hardcodeado, después lo conectamos a la BD
        if ("admin".equals(request.username()) && "admin123".equals(request.password())) {
            String token = jwtUtil.generarToken(request.username());
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(401).build();
    }

    public record LoginRequest(String username, String password) {}
    public record LoginResponse(String token) {}
}