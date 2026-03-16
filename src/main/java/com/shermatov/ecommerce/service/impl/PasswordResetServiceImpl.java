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
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.shermatov.emailsender.application.port.EmailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    @Transactional
    public void forgotPassword(String email) {

        userRepository.findByEmailAndDeletedAtIsNull(email).ifPresent(user -> {

            // Check if user already has a valid unused token
            passwordResetTokenRepository
                    .findTopByUserIdAndUsedAtIsNullOrderByCreatedAtDesc(user.getId())
                    .ifPresent(existingToken -> {

                        if (!existingToken.isExpired(LocalDateTime.now())) {
                            // Reuse existing token instead of creating a new one
                            emailSender.send(
                                    emailComposer.compose(user.getEmail(), existingToken.getToken())
                            );
                            return;
                        }
                    });

            PasswordResetToken token = PasswordResetToken.builder()
                    .user(user)
                    .token(UUID.randomUUID())
                    .expiresAt(LocalDateTime.now().plusMinutes(TOKEN_EXPIRES_MINUTES))
                    .usedAt(null)
                    .build();

            passwordResetTokenRepository.save(token);

            emailSender.send(
                    emailComposer.compose(user.getEmail(), token.getToken())
            );
        });

        // IMPORTANT: Do nothing if email does not exist
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

        PasswordResetToken prt = passwordResetTokenRepository
                .findByTokenAndUsedAtIsNull(tokenUuid)
                .orElseThrow(InvalidTokenException::new);

        LocalDateTime now = LocalDateTime.now();

        if (prt.isExpired(now)) {
            throw new TokenExpiredException();
        }

        User user = prt.getUser();

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        prt.markUsed(now);
        passwordResetTokenRepository.save(prt);
    }

    @Override
    @Transactional
    public void changePassword(User user,
                               String currentPassword,
                               String newPassword) {

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Current password is incorrect"
            );
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }
}