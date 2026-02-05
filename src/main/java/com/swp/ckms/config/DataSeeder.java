package com.swp.ckms.config;

import com.swp.ckms.entity.Role;
import com.swp.ckms.entity.User;
import com.swp.ckms.repository.RoleRepository;
import com.swp.ckms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedAdminUser();
    }

    private void seedAdminUser() {
        String roleName = "Admin";
        Role adminRole = roleRepository.findByRoleName(roleName)
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .roleName(roleName)
                        .privileges(Set.of())
                        .build()));

        if (!userRepository.existsByUsername("admin")) {
            userRepository.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .fullName("System Administrator")
                    .role(adminRole)
                    .isActive(true)
                    .build());
            System.out.println(">>> Seeded default admin user: admin / admin");
        }
    }
}
