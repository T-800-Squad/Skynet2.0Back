package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.CreateUserDTO;
import edu.eci.cvds.Labtools.model.User;

public interface UserService {
    User createUser(CreateUserDTO createUserDTO);
    void deleteUser(String userName);
}
