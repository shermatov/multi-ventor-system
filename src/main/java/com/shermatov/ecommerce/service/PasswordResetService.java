package com.shermatov.ecommerce.service;

import com.shermatov.ecommerce.domain.User;

public interface PasswordResetService {
    void forgotPassword(String email);
    void resetPassword(String token, String newPassword);
    void changePassword(User user, String currentPassword, String newPassword);
}