package com.exam.portal.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.portal.entity.exam.Question;
import com.exam.portal.entity.exam.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Set<Question> findByQuiz(Quiz quiz);
    
}
