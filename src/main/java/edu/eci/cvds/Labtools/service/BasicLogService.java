package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicLogService implements LogService {
    @Autowired
    private HashService hashService;
    @Autowired
    private UserRepository userRepository;

    public String userLog(String email, String password){
        return null;
    }

}
