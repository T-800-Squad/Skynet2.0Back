package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.repository.UserRepository;
import edu.eci.cvds.Labtools.service.EmailVerificationService;
import edu.eci.cvds.Labtools.service.HashService;
import edu.eci.cvds.Labtools.service.LogService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LogService logService;
    private HashService hashService;
    private EmailVerificationService emailVerificationService;
    private UserRepository userRepository;

    public LoginController(LogService logService, HashService hashService, EmailVerificationService emailVerificationService, UserRepository userRepository) {
        this.logService = logService;
        this.hashService = hashService;
        this.emailVerificationService = emailVerificationService;
        this.userRepository = userRepository;
    }

    public String userLog(String email, String password) {
        return null;
    }


    public boolean emailFormtaVerification(String email) {
        return false;
    }



}
