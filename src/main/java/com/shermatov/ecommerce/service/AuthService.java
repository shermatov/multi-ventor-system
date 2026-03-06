package com.shermatov.ecommerce.service;


public interface AuthService {
    UserResponseDTO register(RegisterRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);
}
