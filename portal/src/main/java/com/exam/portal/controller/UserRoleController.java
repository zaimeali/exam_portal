package com.exam.portal.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.exam.portal.dto.UserRoleDTO;
import com.exam.portal.entity.Role;
import com.exam.portal.entity.User;
import com.exam.portal.entity.UserRole;
import com.exam.portal.service.implementation.RoleServiceImpl;
import com.exam.portal.service.implementation.UserRoleServiceImpl;
import com.exam.portal.service.implementation.UserServiceImpl;

@RestController
@RequestMapping("/user-roles")
public class UserRoleController {
    
    @Autowired
    UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    RoleServiceImpl roleServiceImpl;

    @GetMapping
    public List<UserRole> getAllUserRoles() {
        return userRoleServiceImpl.getAllUserRoles();
    }

    @PostMapping
    public UserRole createUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        try {
            Optional<User> user = userServiceImpl.findUserByID((long) userRoleDTO.getUser_id());

            if(user.isEmpty()) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No User Found"
                );
            }

            Optional<Role> role = roleServiceImpl.findRoleByID((long) userRoleDTO.getRole_id());

            if(role.isEmpty()) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No Role Found"
                );
            }
            
            Set<UserRole> userRoles = user.get().getUserRoles();
            UserRole userRole = new UserRole();
            userRole.setRole(role.get());
            userRoles.add(userRole);
            
            User local = user.get();
            local.setUserRoles(userRoles);

            userServiceImpl.updateUser(local);

            return userRoleServiceImpl.createUserRole(user.get(), role.get());
        } catch (Exception err) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteUserRoleByID(@PathVariable Long id) {
        return userRoleServiceImpl.deleteUserRoleByID(id);
    }
}
