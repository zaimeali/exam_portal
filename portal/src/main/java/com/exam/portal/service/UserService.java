package com.exam.portal.service;

import java.util.List;
import java.util.Optional;

import com.exam.portal.entity.User;

public interface UserService {

    List<User> getAllUsers();
    
    User createUser(User user) throws Exception;

    User updateUser(User user) throws Exception;

    Optional<User> findUserByID(Long id);
}
