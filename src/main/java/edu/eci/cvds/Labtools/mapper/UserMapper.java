package edu.eci.cvds.Labtools.mapper;

import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.dto.UserDTO;

public class UserMapper implements GenericMapper<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        //User user = new User();
        //user.setName(dto.getName());
        //user.setEmail(dto.getEmail());
        return null;
    }
}


