package com.pdnguyen.cnnwebapp.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
/**
 * Using Bcrypt algorithm to hash password
 */
public class PasswordEncryptionService {
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
