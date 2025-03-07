package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicLogService implements LogService {
    @Autowired
    private HashService hashService;

    public boolean userLog(UserDTO userDTO){
        return false;
    }

}
