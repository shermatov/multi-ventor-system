package com.shermatov.ecommerce.service;


import com.shermatov.ecommerce.dto.request.LoginRequestDTO;
import com.shermatov.ecommerce.dto.request.UserCreateRequestDTO;
import com.shermatov.ecommerce.dto.response.LoginResponseDTO;
import com.shermatov.ecommerce.dto.response.UserResponseDTO;

public interface AuthService {
    UserResponseDTO register(UserCreateRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);
}

