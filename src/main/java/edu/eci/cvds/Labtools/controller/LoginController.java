package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.dto.UserDTO;
import edu.eci.cvds.Labtools.dto.UserRegisterDTO;
import edu.eci.cvds.Labtools.service.EmailVerificationService;
import edu.eci.cvds.Labtools.service.LogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log")
public class LoginController {

    private LogService logService;
    private EmailVerificationService emailVerificationService;


    public LoginController(LogService logService, EmailVerificationService emailVerificationService) {
        this.logService = logService;
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping
    public UserDTO userLog(@RequestBody UserRegisterDTO userRegisterDTO) {
        return null;
    }

    @GetMapping
    public UserDTO emailFormtaVerification(@RequestBody String email) {
        return null;
    }



}
