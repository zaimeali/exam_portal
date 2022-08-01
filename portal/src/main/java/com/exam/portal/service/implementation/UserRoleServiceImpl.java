package com.exam.portal.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return userRoleRepository.findAll();
    }
}
