package com.exam.portal.service;

import java.util.List;
import java.util.Optional;

import com.exam.portal.entity.Role;

public interface RoleService {

    List<Role> getAllRoles();
    
    Role createRole(Role role);

    Optional<Role> findRoleByID(Long id);

    Role findByName(String name);
}
