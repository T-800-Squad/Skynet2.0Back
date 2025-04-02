package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.CreateUserDTO;
import edu.eci.cvds.Labtools.model.Admin;
import edu.eci.cvds.Labtools.model.BasicUser;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase BasicUser Service que implementa la interfaz UserService.
 * Esta clase se encarga de gestionar las operaciones relacionadas con los usuarios,
 * como la creación y eliminación de usuarios.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@Service
public class BasicUserService implements UserService {
    @Autowired
    private MongoUserRepository userRepository;
    @Autowired
    private HashService hashService;

    /**
     * Crea un nuevo usuario a partir de la información proporcionada en CreateUser DTO.
     *
     * @param createUserDTO Objeto que contiene la información del usuario a crear.
     * @return El usuario creado.
     * @throws IllegalArgumentException Si el rol es inválido o si el usuario ya existe.
     */
    public User createUser(CreateUserDTO createUserDTO) {
        User user;
        if(createUserDTO.getRole().equals("Admin")) {
            user = new Admin();
        }
        else if(createUserDTO.getRole().equals("User")) {
            user = new BasicUser();
        }
        else{
            throw new IllegalArgumentException("Invalid role");
        }
        if(userRepository.findByName(createUserDTO.getUsername()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        user.setName(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(hashService.passwordHashsing(createUserDTO.getPassword()));
        user.setRol(createUserDTO.getRole());
        user = userRepository.save(user);
        return user;
    }

    /**
     * Elimina un usuario por su nombre.
     *
     * @param userName El nombre del usuario a eliminar.
     * @throws IllegalArgumentException Si el usuario no existe.
     */

    public void deleteUser(String userName) {
        if(userRepository.findByName(userName) == null) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteByName(userName);
    }
}
