package com.exam.portal.service;

import java.util.Set;

import com.exam.portal.entity.exam.Quiz;

public interface QuizService {
    
    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getAllQuizzes();

    public Quiz getQuizByID(Long id);

    public void deleteQuizByID(Long id);
}
