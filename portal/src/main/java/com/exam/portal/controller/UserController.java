package com.exam.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.entity.Role;
import com.exam.portal.entity.User;
import com.exam.portal.service.implementation.RoleServiceImpl;
import com.exam.portal.service.implementation.UserRoleServiceImpl;
import com.exam.portal.service.implementation.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    RoleServiceImpl roleServiceImpl;
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userServiceImpl.createUser(user);
    }
}
