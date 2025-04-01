package edu.eci.cvds.Labtools.dto;

/**
 * Clase UserDTO (Data Transfer Object) que representa la información de un usuario.
 * Esta clase se utiliza para transferir datos desde la capa de servicio a la capa de presentación,
 * especialmente en el contexto de autenticación y autorización.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
public class UserDTO {
    private String name;
    private String rol;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }


}