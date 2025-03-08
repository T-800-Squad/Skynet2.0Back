package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.LabToolsException;
import edu.eci.cvds.Labtools.model.UserDTO;
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
    public boolean userLog(@RequestBody UserDTO userDTO) {
        return false;
    }

    @GetMapping
    public boolean emailFormtaVerification(@RequestParam String email) throws LabToolsException {
        return emailVerificationService.emailFormatVerification(email);
    }


}
