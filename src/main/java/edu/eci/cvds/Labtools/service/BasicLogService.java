package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.LabToolsException;
import edu.eci.cvds.Labtools.dto.UserDTO;
import edu.eci.cvds.Labtools.dto.UserRegisterDTO;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Clase BasicLogService que implementa la interfaz LogService.
 * Esta clase se encarga de gestionar el inicio de sesión de los usuarios.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@Service
public class BasicLogService implements LogService {
    @Autowired
    private HashService hashService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MongoUserRepository mongoUserRepository;

    /**
     * Inicia sesión de un usuario utilizando la información proporcionada en UserRegisterDTO.
     *
     * @param userRegisterDTO Objeto que contiene la información del usuario para iniciar sesión.
     * @return Un objeto UserDTO que contiene la información del usuario y el token de sesión.
     * @throws LabToolsException Si el usuario no existe o la contraseña es incorrecta.
     */
    public UserDTO userLog(UserRegisterDTO userRegisterDTO) throws LabToolsException {
        Optional<User> user = mongoUserRepository.findByEmail(userRegisterDTO.getEmail());
        if (user.isEmpty()) {
            throw new LabToolsException(LabToolsException.User_Not_Exist);
        }
        User userDB = user.get();
        if(!hashService.checkPassword(userRegisterDTO.getPassword(), userDB.getPassword())){
            throw new LabToolsException(LabToolsException.Incorrect_Password);
        }
        String token = jwtService.generateToken(userDB.getName(),userDB.getRol().toString());
        mongoUserRepository.save(userDB);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userDB.getName());
        userDTO.setRol(userDB.getRol());
        userDTO.setToken(token);
        return userDTO;
    }

}
