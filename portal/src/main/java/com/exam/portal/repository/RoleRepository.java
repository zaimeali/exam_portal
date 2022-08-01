package com.exam.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exam.portal.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Role findByName(String name);
}
