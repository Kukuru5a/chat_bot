package org.example.vkBot.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTokenGenerator {
    private static final String SECRET_KEY = SecretKeyGenerator.generateSecretKey();
    public static void main(String[] args) {
        String token = generateToken("your-secret-key");
        System.out.println("Generated Token: " + token);
    }

    public static String generateToken(String secretKey) {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + 3600000; // 1 hour

        return Jwts.builder()
                .setSubject("user123") // Устанавливаем имя пользователя или другой идентификатор
                .setIssuedAt(new Date(currentTimeMillis)) // Устанавливаем дату создания токена
                .setExpiration(new Date(expirationTimeMillis)) // Устанавливаем дату истечения срока действия токена
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Подписываем токен с использованием секретного ключа
                .compact();
    }
}
