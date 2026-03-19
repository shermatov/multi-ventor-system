package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.PasswordResetToken;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.exception.InvalidTokenException;
import com.shermatov.ecommerce.exception.TokenAlreadyUsedException;
import com.shermatov.ecommerce.exception.TokenExpiredException;
import com.shermatov.ecommerce.repository.PasswordResetTokenRepository;
import com.shermatov.ecommerce.repository.UserRepository;
import com.shermatov.ecommerce.service.PasswordResetEmailComposer;
import com.shermatov.ecommerce.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.shermatov.emailsender.application.port.EmailSender;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private static final int TOKEN_EXPIRES_MINUTES = 15;

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    private final EmailSender emailSender;
    private final PasswordResetEmailComposer emailComposer;

    @Override
    public void forgotPassword(String email) {
        userRepository.findByEmailAndDeletedAtIsNull(email).ifPresent(user -> {
            PasswordResetToken token = PasswordResetToken.builder()
                    .user(user)
                    .token(UUID.randomUUID())
                    .expiresAt(LocalDateTime.now().plusMinutes(TOKEN_EXPIRES_MINUTES))
                    .usedAt(null)
                    .build();
            passwordResetTokenRepository.save(token);

            emailSender.send(emailComposer.compose(user.getEmail(), token.getToken()));
        });
    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {

        UUID tokenUuid;
        try {
            tokenUuid = UUID.fromString(token);
        } catch (IllegalArgumentException ex) {
            throw new InvalidTokenException();
        }

        PasswordResetToken prt = passwordResetTokenRepository.findByToken(tokenUuid)
                .orElseThrow(InvalidTokenException::new);

        LocalDateTime now = LocalDateTime.now();

        if (prt.isUsed()) {
            throw new TokenAlreadyUsedException();
        }

        if (prt.isExpired(now)) {
            throw new TokenExpiredException();
        }

        User user = prt.getUser();

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        prt.markUsed(now);
        passwordResetTokenRepository.save(prt);
    }
}