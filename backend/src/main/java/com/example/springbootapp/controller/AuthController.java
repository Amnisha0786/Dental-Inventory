package com.example.springbootapp.controller;

import com.example.springbootapp.dto.LoginRequest;
import com.example.springbootapp.dto.LoginResponse;
import com.example.springbootapp.dto.UserProfileDTO;
import com.example.springbootapp.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(auth -> auth.getAuthority().replace("ROLE_", ""))
                .orElse("STAFF");

        LoginResponse response = new LoginResponse();
        response.setToken(jwt);
        response.setUsername(userDetails.getUsername());
        response.setRole(role);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        UserProfileDTO profile = new UserProfileDTO();
        profile.setUsername(userDetails.getUsername());
        profile.setRole(userDetails.getAuthorities().stream()
                .findFirst()
                .map(auth -> auth.getAuthority().replace("ROLE_", ""))
                .orElse("STAFF"));

        return ResponseEntity.ok(profile);
    }
}