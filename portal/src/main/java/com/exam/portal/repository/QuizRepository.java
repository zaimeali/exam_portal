package com.exam.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.portal.entity.exam.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    
}
