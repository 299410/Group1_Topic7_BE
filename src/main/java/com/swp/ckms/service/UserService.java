package com.swp.ckms.service;

import com.swp.ckms.dto.request.CreateUserRequest;
import com.swp.ckms.entity.User;
import com.swp.ckms.enums.UserStatus;
import com.swp.ckms.event.UserCreatedEvent;
import com.swp.ckms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public User createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        String username = generateUniqueUsername(request.getEmail());
        String rawToken = generateSecureToken();
        String tokenHash = hashToken(rawToken);

        User user = User.builder()
                .username(username)
                .email(request.getEmail())
                .fullName(request.getFullName())
                .status(UserStatus.PENDING_VERIFICATION)
                .password(null) // explicit null, though default is null
                .verificationTokenHash(tokenHash)
                .verificationTokenExpiresAt(LocalDateTime.now().plusHours(24))
                .isActive(true)
                // store and kitchen handling omitted for brevity as per plan default
                .build();

        User savedUser = userRepository.save(user);

        eventPublisher.publishEvent(new UserCreatedEvent(this, savedUser, rawToken));

        return savedUser;
    }

    private String generateUniqueUsername(String email) {
        String prefix = email.split("@")[0];
        // simple unique check loop could be added here, but for now just append random suffix
        String randomSuffix = UUID.randomUUID().toString().substring(0, 5); // 5 chars
        String candidate = prefix + "." + randomSuffix;
        
        while (userRepository.existsByUsername(candidate)) {
             randomSuffix = UUID.randomUUID().toString().substring(0, 5);
             candidate = prefix + "." + randomSuffix;
        }
        return candidate;
    }

    private String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    private String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing token", e);
        }
    }
}
