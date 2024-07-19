package ru.mikemind.otus.social_network.service;

import com.fasterxml.uuid.Generators;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikemind.otus.social_network.model.UserCredentialEntity;
import ru.mikemind.otus.social_network.model.UserEntity;
import ru.mikemind.otus.social_network.repository.UserRepository;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final SecureRandom secureRandom = new SecureRandom();

    @Transactional
    public UserEntity register(@NotNull UserEntity userEntity) {
        UUID uuid = Generators.timeBasedEpochRandomGenerator(secureRandom).generate();
        userEntity.setId(uuid.toString());
        userEntity.setCreatedAt(OffsetDateTime.now());
        userEntity.setUpdatedAt(OffsetDateTime.now());
        userEntity.setUserCredential(secureCredential(userEntity.getUserCredential()));
        userRepository.save(userEntity);
        return userEntity;
    }

    private UserCredentialEntity secureCredential(UserCredentialEntity rawUserCredential) {
        return UserCredentialEntity.builder()
                .password(passwordEncoder.encode(rawUserCredential.getPassword()))
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
    }

    public Optional<UserEntity> getById(@NotNull String id) {
        return userRepository.getById(id);
    }

    public List<UserEntity> findByFirstNameAndSecondName(@NotNull String firstName, @NotNull String secondName) {
        return userRepository.findAllByFirstNameAndSecondName("%" + firstName + "%", "%" + secondName + "%");
    }

}
