package com.exam.portal.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.portal.entity.exam.Category;
import com.exam.portal.entity.exam.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    
    Set<Quiz> findByCategory(Category category);

    Set<Quiz> findByActiveTrue();

    Set<Quiz> findByCategoryAndActiveTrue(Category category);
}
