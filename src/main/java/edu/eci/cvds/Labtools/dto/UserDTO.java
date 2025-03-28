package edu.eci.cvds.Labtools.dto;


import edu.eci.cvds.Labtools.model.Role;

public class UserDTO {
    private String name;
    private Role rol;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserDTO userDTO) {
            return name.equals(userDTO.name) && rol == userDTO.rol;
        }
        return false;
    }
}