package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.LabToolsException;
import org.springframework.stereotype.Service;

/**
 * Clase BasicEmailVerificationService que implementa la interfaz EmailVerificationService.
 * Esta clase se encarga de verificar el formato de un correo electrónico.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@Service
public class BasicEmailVerificationService implements EmailVerificationService {
    private String domain = "mail.escuelaing.edu.co";

    /**
     * Verifica el formato de un correo electrónico.
     *
     * @param email El correo electrónico a verificar.
     * @return true si el formato del correo electrónico es válido.
     * @throws LabToolsException Si el correo electrónico es vacío, no contiene '@' o no termina con el dominio permitido.
     */
    public boolean emailFormatVerification(String email) throws LabToolsException {
        if(email.isEmpty()){
            throw new LabToolsException(LabToolsException.Void_Email);
        }
        if(!email.contains("@")){
            throw new LabToolsException(LabToolsException.Email_Not_Found);
        }
        if(!email.endsWith(domain)){
            throw new LabToolsException(LabToolsException.Email_Domain_Not_Found );
        }
        return true;
    }
}
