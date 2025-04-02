package edu.eci.cvds.Labtools.model;

/**
 * Clase Admin que representa un usuario con rol de administrador.
 * Esta clase extiende la clase User y establece el rol del usuario como "Admin".
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
public class Admin extends User{

    /**
     * Constructor de la clase Admin.
     * Inicializa un nuevo objeto Admin y establece el rol como "Admin".
     */
    public Admin() {
        super();
        rol = "Admin";
    }
}
