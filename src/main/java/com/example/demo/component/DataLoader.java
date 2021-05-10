package com.example.demo.component;

import com.example.demo.entity.Tamplate.Position;
import com.example.demo.entity.enums.Huquq;
import com.example.demo.repository.PositionRepository;

import com.example.demo.utils.AppConstants;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    final UserRepository userRepository;
    final PositionRepository positionRepository;
    final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PositionRepository positionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
        this.passwordEncoder = passwordEncoder;
    }
@Value("${spring.datasource.initialization-mode}")
private String initialMode;
    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Huquq[] huquqs = Huquq.values();
            Position admin = positionRepository.save(new Position(
                    AppConstants.ADMIN,
                    Arrays.asList(huquqs),
                    "Sistema egasi"
            ));
            Position user = positionRepository.save(new Position(
                    AppConstants.USER,
                    Arrays.asList(),
                    "Oddiy foydalanuvchi"
            ));
        }
    }
}
