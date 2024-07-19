package ru.mikemind.otus.social_network.service;

import io.jsonwebtoken.Jwts;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikemind.otus.social_network.exception.UserNotAuthenticatedException;
import ru.mikemind.otus.social_network.model.UserCredentialEntity;
import ru.mikemind.otus.social_network.model.UserEntity;
import ru.mikemind.otus.social_network.repository.UserRepository;

import java.security.PrivateKey;
import java.time.Instant;
import java.util.Date;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PrivateKey privateKey;

    @Value("${jwt.expiration_minutes}")
    private Long expirationMinutes;

    public String login(@NotNull UserEntity signingInUser) {
        UserEntity registeredUser = userRepository.getById(signingInUser.getId())
                .orElseThrow(UserNotAuthenticatedException::new);

        UserCredentialEntity registeredUserCredential = userRepository.getCredentials(registeredUser.getPkId())
                .orElseThrow(UserNotAuthenticatedException::new);

        if (passwordEncoder.matches(signingInUser.getUserCredential().getPassword(),
                registeredUserCredential.getPassword())) {
            return issueAccessToken(registeredUser);
        }
        throw new UserNotAuthenticatedException();
    }

    private String issueAccessToken(@NotNull UserEntity userEntity) {
        Instant issuedAt = Instant.now();
        Instant expiration = issuedAt.plusSeconds(60 * expirationMinutes);
        return Jwts.builder().header().keyId("0").and()
                .subject(userEntity.getId())
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiration))
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }
}
