package com.swp.ckms.config;

import com.swp.ckms.entity.Privilege;
import com.swp.ckms.entity.Role;
import com.swp.ckms.entity.User;
import com.swp.ckms.enums.AppPrivilege;
import com.swp.ckms.repository.PrivilegeRepository;
import com.swp.ckms.repository.RoleRepository;
import com.swp.ckms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Set<Privilege> allPrivileges = seedPrivileges();
        seedAdminUser(allPrivileges);
    }

    private Set<Privilege> seedPrivileges() {
        return Arrays.stream(AppPrivilege.values())
                .map(appPrivilege -> privilegeRepository.findByCode(appPrivilege.getCode())
                        .orElseGet(() -> privilegeRepository.save(Privilege.builder()
                                .code(appPrivilege.getCode())
                                .description(appPrivilege.getDescription())
                                .build())))
                .collect(Collectors.toSet());
    }

    private void seedAdminUser(Set<Privilege> allPrivileges) {
        String roleName = "Admin";
        
        Role adminRole = roleRepository.findByRoleName(roleName)
                .map(role -> {
                    // Update privileges if role exists
                    role.setPrivileges(allPrivileges);
                    return roleRepository.save(role); 
                })
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .roleName(roleName)
                        .privileges(allPrivileges)
                        .build()));

        if (!userRepository.existsByUsername("admin")) {
            userRepository.save(User.builder()
                    .username("admin")
                    .email("admin@ckms.com")
                    .password(passwordEncoder.encode("admin"))
                    .fullName("System Administrator")
                    .status(com.swp.ckms.enums.UserStatus.ACTIVE)
                    .role(adminRole)
                    .isActive(true)
                    .build());
            System.out.println(">>> Seeded default admin user: admin / admin");
        } else {
            // Ensure existing admin user has the correct role reference if needed
            // (Assumed already set, but good to double check in a real migration scenario)
             System.out.println(">>> Admin user already exists. Privileges updated.");
        }
    }
}
