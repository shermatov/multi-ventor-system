package com.shermatov.ecommerce.service;


import com.shermatov.ecommerce.dto.request.LoginRequestDTO;
import com.shermatov.ecommerce.dto.request.RegisterRequestDTO;
import com.shermatov.ecommerce.dto.response.LoginResponseDTO;
import com.shermatov.ecommerce.dto.response.UserResponseDTO;

public interface AuthService {
    UserResponseDTO register(RegisterRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);
}
