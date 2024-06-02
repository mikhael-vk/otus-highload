package ru.mikemind.otus.social_network.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
@RequiredArgsConstructor
public class KeysConfiguration {

    @Value("${jwt.private_key.path}")
    private String privateKeyPath;
    @Value("${jwt.public_key.path}")
    private String publicKeyPath;
    @Value("${jwt.alg}")
    private String keyFactoryAlgorithm;
    private final ResourceLoader resourceLoader;

    @Bean
    public PrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = resourceLoader.getResource(privateKeyPath).getInputStream().readAllBytes();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance(keyFactoryAlgorithm);
        return kf.generatePrivate(spec);
    }

    @Bean
    public PublicKey getPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = resourceLoader.getResource(publicKeyPath).getInputStream().readAllBytes();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance(keyFactoryAlgorithm);
        return kf.generatePublic(spec);
    }

}
