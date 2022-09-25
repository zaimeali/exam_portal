package com.exam.portal.controller;

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

import com.exam.portal.entity.exam.Quiz;
import com.exam.portal.service.implementation.QuizServiceImpl;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin("*")
public class QuizController {
    
    @Autowired
    private QuizServiceImpl quizServiceImpl;

    @PostMapping("/")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(
            quizServiceImpl.addQuiz(quiz)
        );
    }

    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(
            quizServiceImpl.updateQuiz(quiz)
        );
    }

    @GetMapping("/")
    public ResponseEntity<Set<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(
            quizServiceImpl.getAllQuizzes()
        );
    }

    @GetMapping("/{quizID}")
    public ResponseEntity<Quiz> getQuizByID(@PathVariable("quizID") Long quizID) {
        return ResponseEntity.ok(
            quizServiceImpl.getQuizByID(quizID)
        );
    }

    @DeleteMapping("/{quizID}")
    public void deleteQuizByID(@PathVariable("quizID") Long quizID) {
        quizServiceImpl.deleteQuizByID(quizID);
    }
}
