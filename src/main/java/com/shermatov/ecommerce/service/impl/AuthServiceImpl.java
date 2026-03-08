package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.LoginRequestDTO;
import com.shermatov.ecommerce.dto.request.RegisterRequestDTO;
import com.shermatov.ecommerce.dto.response.LoginResponseDTO;
import com.shermatov.ecommerce.dto.response.UserResponseDTO;
import com.shermatov.ecommerce.exception.EmailAlreadyUsedException;
import com.shermatov.ecommerce.repository.UserRepository;
import com.shermatov.ecommerce.security.JwtService;
import com.shermatov.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional
    public UserResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.existsByEmailAndDeletedAtIsNull(request.getEmail())) {
            throw new EmailAlreadyUsedException("Email is already in use");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User saved = userRepository.save(user);
        return new UserResponseDTO(saved.getEmail(), saved.getPassword(), saved.getFirstName(), saved.getLastName());
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) throws BadCredentialsException {
        User user = userRepository.findByEmailAndDeletedAtIsNull(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new LoginResponseDTO(token);
    }
}
