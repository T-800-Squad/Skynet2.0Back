package edu.eci.cvds.Labtools.service;

import org.springframework.stereotype.Service;

@Service
public class BasicEmailVerificationService implements EmailVerificationService {
    public boolean emailFormatVerification(String email) {
        return false;
    }
}
