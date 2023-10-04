package com.mobileshop.dto;

import com.mobileshop.entities.Role;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterDto {

    private String username;
    private String password;
    private String role;

    private Set<Role> roles;


}
