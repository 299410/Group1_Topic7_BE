package com.swp.ckms.controller;

import com.swp.ckms.dto.request.LoginRequest;
import com.swp.ckms.dto.response.LoginResponse;
import com.swp.ckms.dto.request.LogoutRequest;
import com.swp.ckms.dto.request.ActivateAccountRequest;
import com.swp.ckms.service.AuthService;
import com.swp.ckms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate user and return JWT token")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }



    @PostMapping("/activate")
    @Operation(summary = "Activate account", description = "Verify token and set password")
    public ResponseEntity<String> activateAccount(@Valid @RequestBody ActivateAccountRequest request) {
        userService.activateAccount(request);
        return ResponseEntity.ok("Account activated successfully");
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout user", description = "Invalidate Refresh Token")
    public ResponseEntity<String> logout(@Valid @RequestBody LogoutRequest logoutRequest) {
        authService.logout(logoutRequest);
        return ResponseEntity.ok("Logged out successfully");
    }
}
