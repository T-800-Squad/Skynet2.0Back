package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.LabToolsException;
import edu.eci.cvds.Labtools.dto.UserDTO;
import edu.eci.cvds.Labtools.dto.UserRegisterDTO;
import edu.eci.cvds.Labtools.service.EmailVerificationService;
import edu.eci.cvds.Labtools.service.LogService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;


/**
 * Controlador REST para la gestión de inicio de sesión y verificación de correos electrónicos.
 * Este controlador proporciona endpoints para iniciar sesión y verificar el formato de un correo electrónico.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    private LogService logService;
    private EmailVerificationService emailVerificationService;

    /**
     * Constructor para inyectar los servicios necesarios.
     *
     * @param logService Servicio de inicio de sesión a inyectar.
     * @param emailVerificationService Servicio de verificación de correos electrónicos a inyectar.
     */
    public LoginController(LogService logService, EmailVerificationService emailVerificationService) {
        this.logService = logService;
        this.emailVerificationService = emailVerificationService;
    }

    /**
     * Endpoint para iniciar sesión de un usuario.
     *
     * @param userRegisterDTO DTO que contiene la información del usuario que intenta iniciar sesión.
     * @return UserDTO que representa al usuario que ha iniciado sesión.
     * @throws LabToolsException Si ocurre un error durante el proceso de inicio de sesión.
     */
    @PostMapping
    public UserDTO userLog(@RequestBody UserRegisterDTO userRegisterDTO) throws LabToolsException {
        return logService.userLog(userRegisterDTO);
    }

    /**
     * Endpoint para verificar el formato de un correo electrónico.
     *
     * @param email El correo electrónico a verificar.
     * @return boolean que indica si el formato del correo electrónico es válido.
     * @throws LabToolsException Si ocurre un error durante la verificación del formato del correo electrónico.
     */
    @GetMapping
    public boolean emailFormtaVerification(@RequestParam String email) throws LabToolsException {
        return emailVerificationService.emailFormatVerification(email);
    }

}
