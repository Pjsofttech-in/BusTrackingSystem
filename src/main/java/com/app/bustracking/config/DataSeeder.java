package com.app.bustracking.config;

import com.app.bustracking.model.UserModel;
import com.app.bustracking.repository.UserRepository;
import com.app.bustracking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    @Bean
    CommandLineRunner seedDefaultAdmin(UserRepository userRepository, UserService userService) {
        return args -> {
            if (userRepository.existsByUsername("admin")) {
                log.info("Default admin already exists — skipping seed");
                return;
            }

            UserModel admin = new UserModel();
            admin.setUsername("admin");
            admin.setPassword("admin123");   // userService.save() BCrypt-encodes it
            admin.setRole("ADMIN");
            admin.setRoleId(1L);

            userService.save(admin);
            log.warn("═══════════════════════════════════════════════");
            log.warn("  Seeded default admin account");
            log.warn("     username: admin");
            log.warn("     password: admin123");
            log.warn("  ⚠  CHANGE THIS PASSWORD IMMEDIATELY in production");
            log.warn("═══════════════════════════════════════════════");
        };
    }
}