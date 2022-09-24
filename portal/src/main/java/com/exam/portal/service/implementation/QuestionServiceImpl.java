package com.exam.portal.service.implementation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.entity.exam.Question;
import com.exam.portal.entity.exam.Quiz;
import com.exam.portal.repository.QuestionRepository;
import com.exam.portal.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> getAllQuestions() {
        return new HashSet<>(questionRepository.findAll());
    }

    @Override
    public Question getQuestionByID(long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public void deleteQuestionByID(long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }
}
