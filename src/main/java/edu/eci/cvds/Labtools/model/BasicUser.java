package edu.eci.cvds.Labtools.model;

/**
 * Clase BasicUser que representa un usuario con rol básico.
 * Esta clase extiende la clase User y establece el rol del usuario como "User".
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
public class BasicUser extends User{

    /**
     * Constructor de la clase BasicUser.
     * Inicializa un nuevo objeto BasicUser y establece el rol como "User".
     */
    public BasicUser() {
        super();
        rol = "User";
    }
}
