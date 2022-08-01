package com.exam.portal.service;

import java.util.List;

import com.exam.portal.entity.Role;

public interface RoleService {

    List<Role> getAllRoles();
    
    Role createRole(Role role);

    Role findByID(Long id);
}
