package edu.eci.cvds.Labtools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Clase ExceptionController que maneja excepciones lanzadas en la aplicación.
 * Utiliza @ControllerAdvice para interceptar excepciones y devolver respuestas adecuadas.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@ControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * Maneja excepciones de tipo LabToolsException.
     *
     * @param e La excepción que se ha lanzado.
     * @return Una respuesta con el estado HTTP 400 (BAD REQUEST) y el mensaje de la excepción.
     */
    @ExceptionHandler(LabToolsException.class)
    public ResponseEntity<String> handleLabToolsException(LabToolsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Maneja excepciones de tipo IllegalArgumentException.
     *
     * @param e La excepción que se ha lanzado.
     * @return Una respuesta con el estado HTTP 400 (BAD REQUEST) y el mensaje de la excepción.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentExceptionException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
