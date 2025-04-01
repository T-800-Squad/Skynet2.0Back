package edu.eci.cvds.Labtools.service;

import com.lambdaworks.crypto.SCryptUtil;
import org.springframework.stereotype.Service;


/**
 * Clase BasicHashService que implementa la interfaz HashService.
 * Esta clase se encarga de gestionar el hashing de contraseñas utilizando el algoritmo SCrypt.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */

@Service
public class BasicHashService implements HashService {

    /**
     * Genera un hash para una contraseña utilizando el algoritmo SCrypt.
     *
     * @param password La contraseña a hashear.
     * @return La contraseña hasheada como una cadena.
     */
    public String passwordHashsing(String password) {
        return SCryptUtil.scrypt(password,16384, 8, 1);
    }

    /**
     * Verifica si una contraseña coincide con un hash dado.
     *
     * @param password La contraseña a verificar.
     * @param hashedPassword El hash de la contraseña con la que se va a comparar.
     * @return true si la contraseña coincide con el hash, false en caso contrario.
     */
    public boolean checkPassword(String password,String hashedPassword) {
        return SCryptUtil.check(password, hashedPassword);
    }
}
