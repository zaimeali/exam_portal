package com.exam.portal.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String profile;
    private boolean is_enabled;
    private boolean is_admin;
}
