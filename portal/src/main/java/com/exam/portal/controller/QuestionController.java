package com.exam.portal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.entity.exam.Question;
import com.exam.portal.entity.exam.Quiz;
import com.exam.portal.service.implementation.QuestionServiceImpl;
import com.exam.portal.service.implementation.QuizServiceImpl;

@RestController
@RequestMapping("/api/question")
@CrossOrigin("*")
public class QuestionController {
    
    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Autowired
    private QuizServiceImpl quizServiceImpl;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(
            questionServiceImpl.addQuestion(question)
        );
    }
    
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(
            questionServiceImpl.updateQuestion(question)
        );
    }

    @GetMapping("/")
    public ResponseEntity<Set<Question>> getAllQuestions() {
        return ResponseEntity.ok(
            questionServiceImpl.getAllQuestions()
        );
    }

    @GetMapping("/quiz/{quizID}")
    public ResponseEntity<Set<Question>> getQuestionByQuizID(@PathVariable("quizID") Long quizID) {
        // Quiz quiz = new Quiz();
        // quiz.setQuizID(quizID);

        // return ResponseEntity.ok(
        //     questionServiceImpl.getQuestionsOfQuiz(quiz)
        // );

        Quiz quiz = quizServiceImpl.getQuizByID(quizID);

        Set<Question> questions = questionServiceImpl.getQuestionsOfQuiz(quiz); 

        List<Question> questionList = new ArrayList<>(questions);
        
        Collections.shuffle(questionList);

        if(questionList.size() > Integer.parseInt("" + quiz.getNumberOfQuestions())) {
            questionList = questionList.subList(0, Integer.parseInt("" + quiz.getNumberOfQuestions()) + 1);
        }

        Collections.shuffle(questionList);

        return ResponseEntity.ok(
            new HashSet<>(questionList)
        );
    }

    @GetMapping("/{questionID}")
    public ResponseEntity<Question> getQuestionByID(@PathVariable("questionID") Long questionID) {
        return ResponseEntity.ok(
            questionServiceImpl.getQuestionByID(questionID)
        );
    }

    @DeleteMapping("/{questionID}")
    public void deleteQuestionByID(@PathVariable("questionID") Long questionID) {
        questionServiceImpl.deleteQuestionByID(questionID);
    }
}
