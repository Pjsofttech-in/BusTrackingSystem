package com.app.bustracking.controller;

import com.app.bustracking.model.UserModel;
import com.app.bustracking.service.UserService;
import com.app.bustracking.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        log.info("Login attempt: username='{}'", username);

        if (username == null || username.isBlank() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Username and password are required"));
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (UsernameNotFoundException e) {
            log.warn("Login failed — user not found: '{}'", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        } catch (BadCredentialsException e) {
            log.warn("Login failed — bad password for user: '{}'", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        } catch (AuthenticationException e) {
            log.warn("Login failed — {} for user: '{}'", e.getClass().getSimpleName(), username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }

        final UserDetails userDetails = userService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);

        UserModel user = (UserModel) userDetails;

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("role", user.getRole());
        response.put("roleId", user.getRoleId());
        response.put("username", user.getUsername());

        log.info("Login success: username='{}', role='{}'", username, user.getRole());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel user) {
        if (user.getUsername() == null || user.getUsername().isBlank()
                || user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "username and password are required"));
        }
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Username already exists"));
        }
        UserModel saved = userService.save(user);
        log.info("Registered user '{}' with role '{}'", saved.getUsername(), saved.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "User registered successfully",
                "username", saved.getUsername(),
                "role", saved.getRole()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}