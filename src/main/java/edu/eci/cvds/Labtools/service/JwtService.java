package edu.eci.cvds.Labtools.service;

import com.auth0.jwt.algorithms.Algorithm;
import edu.eci.cvds.Labtools.LabToolsException;
import org.springframework.stereotype.Service;



import com.auth0.jwt.JWT;

import java.util.Date;


@Service
public class JwtService {
    private final String SECRET_KEY = "ContraseñaSuperSecreta123";
    private final long EXPIRATION_TIME = 60 * 60 * 1000;

    public String generateToken(String userName, String role) {
        return JWT.create()
                .withClaim("userName",userName)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String getUserNameFromToken(String token) {
        String userName = null;
        userName = JWT.decode(token).getClaim("userName").asString();
        return userName;
    }

    public String getRoleFromToken(String token)  {
        String role = null;
        role = JWT.decode(token).getClaim("role").asString();
        return role;
    }
    public boolean validateUserIsAdmin(String header)  {
        String token = header.substring(7);
        if(getRoleFromToken(token).equals("Admin")){
            return true;
        }
        return false;
    }
}
