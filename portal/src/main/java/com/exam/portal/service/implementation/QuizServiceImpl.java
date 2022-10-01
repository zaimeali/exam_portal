package com.exam.portal.service.implementation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.entity.exam.Category;
import com.exam.portal.entity.exam.Quiz;
import com.exam.portal.repository.QuizRepository;
import com.exam.portal.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
    
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getAllQuizzes() {
        return new HashSet<>(quizRepository.findAll());
    }

    @Override
    public Quiz getQuizByID(Long id) {
        return quizRepository.findById(id).get();
    }

    @Override
    public void deleteQuizByID(Long id) {
        quizRepository.deleteById(id);
    }

    public Set<Quiz> getQuizzesOfCategory(Long categoryID) {
        Category category = new Category();
        category.setCategoryID(categoryID);
        return quizRepository.findByCategory(category);
    }

    @Override
    public Set<Quiz> getActiveQuizzes() {
        return quizRepository.findByActiveTrue();
    }

    @Override
    public Set<Quiz> getActiveQuizzesByCategory(Long categoryID) {
        Category category = new Category();
        category.setCategoryID(categoryID);
        return quizRepository.findByCategoryAndActiveTrue(category);
    }
}
