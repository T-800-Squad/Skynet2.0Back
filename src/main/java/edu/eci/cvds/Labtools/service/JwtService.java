package edu.eci.cvds.Labtools.service;

import com.auth0.jwt.algorithms.Algorithm;
import edu.eci.cvds.Labtools.LabToolsException;
import org.springframework.stereotype.Service;



import com.auth0.jwt.JWT;

import java.util.Date;

/**
 * Clase JwtService que se encarga de gestionar la creación y validación de tokens JWT.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@Service
public class JwtService {
    private final String SECRET_KEY = "ContraseñaSuperSecreta123";
    private final long EXPIRATION_TIME = 60 * 60 * 1000;

    /**
     * Genera un token JWT para un usuario con un nombre y rol específicos.
     *
     * @param userName El nombre del usuario para el que se genera el token.
     * @param role El rol del usuario.
     * @return El token JWT generado como una cadena.
     */
    public String generateToken(String userName, String role) {
        return JWT.create()
                .withClaim("userName",userName)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * Extrae el nombre de usuario de un token JWT.
     *
     * @param token El token del que se extraerá el nombre de usuario.
     * @return El nombre de usuario extraído del token.
     */
    public String getUserNameFromToken(String token) {
        String userName = null;
        userName = JWT.decode(token).getClaim("userName").asString();
        return userName;
    }

    /**
     * Extrae el rol de un token JWT.
     *
     * @param token El token del que se extraerá el rol.
     * @return El rol extraído del token.
     */
    public String getRoleFromToken(String token)  {
        String role = null;
        role = JWT.decode(token).getClaim("role").asString();
        return role;
    }

    /**
     * Validar si el usuario asociado a un token JWT tiene el rol de Admin.
     *
     * @param header El encabezado que contiene el token.
     * @return true si el rol es Admin, false en caso contrario.
     */
    public boolean validateUserIsAdmin(String header)  {
        String token = header.substring(7);
        if(getRoleFromToken(token).equals("Admin")){
            return true;
        }
        return false;
    }
}
