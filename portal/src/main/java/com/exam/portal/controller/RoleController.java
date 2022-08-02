package com.exam.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.entity.Role;
import com.exam.portal.service.implementation.RoleServiceImpl;

@RestController
@RequestMapping("/roles")
public class RoleController {
    
    @Autowired
    RoleServiceImpl roleServiceImpl;

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleServiceImpl.createRole(role);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleServiceImpl.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleByID(@PathVariable Long id) {
        return roleServiceImpl.findRoleByID(id).get();
    }
}
