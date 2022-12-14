package com.exam.portal.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.exam.portal.entity.Role;
import com.exam.portal.entity.User;
import com.exam.portal.repository.UserRepository;
import com.exam.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleServiceImpl roleServiceImpl;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User user) {

        Optional<User> isExistUser = userRepository.findByUsername(user.getUsername().toLowerCase());

        if(isExistUser.isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Username already exist"
            );
        }

        // Encrypting Password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));


        List<Role> roles = roleServiceImpl.getAllRoles();

        if(roles.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "No Role Found"
            );
        }

        Role role = roleServiceImpl.findByName("USER");
        user.addUserRole(role);

        // Role adminRole = roleServiceImpl.findByName("ADMIN");
        // user.addUserRole(adminRole);

        user.setEmail(user.getEmail().toLowerCase());
        user.setUsername(user.getUsername().toLowerCase());
        user.setProfile("default.png");

        User createdUser = userRepository.save(user);

        return createdUser;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception err) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public Optional<User> findUserByID(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No User Found"
            );
        }

        return user;
    }

    @Override
    public User updateUser(User user) throws Exception {
        try {
            Long id = user.getId();

            if(userRepository.findById(id).isEmpty()) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User not exist"
                );
            }

            return userRepository.save(user);
        } catch (Exception err) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No user exist with this username"
            );
        }

        return user.get();
    }

    @Override
    public boolean deleteUserByID(Long id) {
        try {

            Optional<User> user = userRepository.findById(id);

            if(user.isEmpty()) {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No user found with the provided ID"
                );
            }

            userRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
