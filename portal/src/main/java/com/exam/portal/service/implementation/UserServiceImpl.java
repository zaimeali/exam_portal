package com.exam.portal.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.exam.portal.entity.Role;
import com.exam.portal.entity.User;
import com.exam.portal.entity.UserRole;
import com.exam.portal.repository.UserRepository;
import com.exam.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    RoleServiceImpl roleServiceImpl;

    @Override
    public User createUser(User user) {
        List<Role> roles = roleServiceImpl.getAllRoles();

        if(roles.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "No Role Found"
            );
        }

        Role role = roleServiceImpl.findByName("USER");

        UserRole userRole = new UserRole();
        userRole.setRole(role);

        Set<UserRole> userRoles = new HashSet<>();

        userRoles.add(userRole);
        
        if(user.is_admin()) {
            Role admin = roleServiceImpl.findByName("ADMIN");
            UserRole adminRole = new UserRole();
            adminRole.setRole(admin);
            userRoles.add(adminRole);
        }

        User createdUser = userRepository.save(user);

        for(UserRole ur : userRoles) {
            userRoleServiceImpl.createUserRole(createdUser, ur.getRole());
        }

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
    
}
