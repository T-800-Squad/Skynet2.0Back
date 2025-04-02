package edu.eci.cvds.Labtools.dto;

/**
 * Clase UserRegisterDTO (Data Transfer Object) que representa la información necesaria
 * para registrar un nuevo usuario. Esta clase se utiliza para transferir datos desde la capa
 * de presentación a la capa de servicio durante el proceso de registro.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
public class UserRegisterDTO {
    private String email;
    private String password;

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
}
