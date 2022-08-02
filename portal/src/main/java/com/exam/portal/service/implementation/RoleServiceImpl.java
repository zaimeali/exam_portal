package com.exam.portal.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.exam.portal.entity.Role;
import com.exam.portal.repository.RoleRepository;
import com.exam.portal.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        try {
            return roleRepository.save(role);
        } catch (Exception err) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Error in creating role"
            );
        }
    }

    @Override
    public Optional<Role> findRoleByID(Long id) {
        try {
            Optional<Role> role = roleRepository.findById(id);

            if(role.isEmpty()) {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No Role Found"
                );
            }

            return roleRepository.findById(id);
        } catch (Exception err) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public List<Role> getAllRoles() {
        try {
            return roleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        } catch (Exception err) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
    
}
