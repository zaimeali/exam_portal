package com.exam.portal.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import com.exam.portal.entity.User;
import com.exam.portal.entity.UserRole;
import com.exam.portal.repository.RoleRepository;
import com.exam.portal.repository.UserRepository;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        
        User local = userRepository.findByUserName(user.getUsername());

        if(local != null) {
            System.out.println("User already exists");

            throw new ResponseStatusException(
                HttpStatusCode.valueOf(401),
                "User with this username already exists"
            );
        } else {
            for(UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = userRepository.save(user);
        }

        return local;
    }
    
}
