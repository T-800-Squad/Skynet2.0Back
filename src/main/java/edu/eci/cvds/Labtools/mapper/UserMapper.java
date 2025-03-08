package edu.eci.cvds.Labtools.mapper;

import edu.eci.cvds.Labtools.model.Admin;
import edu.eci.cvds.Labtools.model.BasicUser;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.model.UserDTO;

import java.util.List;

public class UserMapper implements GenericMapper<User, UserDTO> {

    List<String> admins = List.of("1001", "1002", "1003");

    @Override
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = checkUser(dto);
        user.setUserId(dto.getUserId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setLogged(true);
        return user;
    }

    public User checkUser(UserDTO dto){
        String userId = dto.getUserId();
        if (admins.contains(userId)) {
            return new Admin();
        } else{
            return new BasicUser();
        }
    }
}


