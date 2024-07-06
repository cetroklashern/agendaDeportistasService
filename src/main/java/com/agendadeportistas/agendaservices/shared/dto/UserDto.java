package com.agendadeportistas.agendaservices.shared.dto;

import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String username;
    private String nombre;
    private String email;
    private String password;
    private String encryptedPassword;
}
