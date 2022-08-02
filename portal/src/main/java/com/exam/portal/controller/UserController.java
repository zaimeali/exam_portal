package com.exam.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.entity.User;
import com.exam.portal.service.implementation.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userServiceImpl.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Long id) {
        return userServiceImpl.findUserByID(id).get();
    }
}