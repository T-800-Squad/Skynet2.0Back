package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.service.EmailVerificationService;
import edu.eci.cvds.Labtools.service.LogService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LogService logService;
    private EmailVerificationService emailVerificationService;


    public LoginController(LogService logService, EmailVerificationService emailVerificationService) {
        this.logService = logService;
        this.emailVerificationService = emailVerificationService;

    }

    public String userLog(String email, String password) {
        return null;
    }


    public boolean emailFormtaVerification(String email) {
        return false;
    }



}
