package com.exam.portal.service.implementation;

import java.util.Set;

import com.exam.portal.entity.User;
import com.exam.portal.entity.UserRole;

public interface UserService {
    
    User createUser(User user, Set<UserRole> userRoles) throws Exception;
}
