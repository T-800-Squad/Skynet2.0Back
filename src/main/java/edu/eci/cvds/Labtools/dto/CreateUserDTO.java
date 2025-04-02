package edu.eci.cvds.Labtools.dto;

/**
 * Clase CreateUser DTO (Data Transfer Object) que representa la información necesaria
 * para crear un nuevo usuario. Esta clase se utiliza para transferir datos desde la capa
 * de presentación a la capa de servicio.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
    private String role;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
