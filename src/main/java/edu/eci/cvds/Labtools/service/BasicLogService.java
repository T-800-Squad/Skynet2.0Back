package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicLogService implements LogService {
    @Autowired
    private HashService hashService;

    public boolean userLog(UserRegisterDTO userRegisterDTO){
        return false;
    }

}
