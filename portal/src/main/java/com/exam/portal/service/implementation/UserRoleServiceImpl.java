package com.exam.portal.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.exam.portal.entity.Role;
import com.exam.portal.entity.User;
import com.exam.portal.entity.UserRole;
import com.exam.portal.repository.UserRoleRepository;
import com.exam.portal.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole createUserRole(User user, Role role) {
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        return userRoleRepository.save(userRole);
    }

    @Override
    public Optional<UserRole> findUserRoleByID(Long id) {
        Optional<UserRole> userRole = userRoleRepository.findById(id);

        if(!(userRole.isPresent())) {
            return Optional.empty();
        }

        return userRole;
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        try {
            return userRoleRepository.findAll();
        } catch (Exception err) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public List<UserRole> findUserRolesByUserID(User user) {
        return userRoleRepository.findByUser(user);
    }

    @Override
    public boolean deleteUserRoles(List<UserRole> userRoles) {
        userRoleRepository.deleteAll(userRoles);
        return true;
    }

    @Override
    public boolean deleteUserRoleByID(Long id) {
        userRoleRepository.deleteById(id);
        return true;
    }
}
