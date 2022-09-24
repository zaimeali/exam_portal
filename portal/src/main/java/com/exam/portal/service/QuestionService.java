package com.exam.portal.service;

import java.util.Set;

import com.exam.portal.entity.exam.Question;
import com.exam.portal.entity.exam.Quiz;

public interface QuestionService {
    
    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Set<Question> getAllQuestions();

    public Question getQuestionByID(long id);

    public void deleteQuestionByID(long id);

    public Set<Question> getQuestionsOfQuiz(Quiz quiz);
}
