package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.LabToolsException;
import edu.eci.cvds.Labtools.dto.UserDTO;
import edu.eci.cvds.Labtools.dto.UserRegisterDTO;
import edu.eci.cvds.Labtools.service.EmailVerificationService;
import edu.eci.cvds.Labtools.service.LogService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")

public class LoginController {

    private LogService logService;
    private EmailVerificationService emailVerificationService;


    public LoginController(LogService logService, EmailVerificationService emailVerificationService) {
        this.logService = logService;
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping
    public UserDTO userLog(@RequestBody UserRegisterDTO userRegisterDTO, HttpServletResponse response) throws LabToolsException {
        UserDTO userDTO = logService.userLog(userRegisterDTO);

        return userDTO;
    }

    @GetMapping
    public boolean emailFormtaVerification(@RequestParam String email) throws LabToolsException {
        return emailVerificationService.emailFormatVerification(email);
    }



}
