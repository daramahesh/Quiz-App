package com.marc.controller;

import com.marc.dto.QuestionWrapper;
import com.marc.dto.QuizDto;
import com.marc.dto.QuizResponse;
import com.marc.service.impl.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/quiz")
public class QuizController {
    @Autowired
    private QuizServiceImpl quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return this.quizService.createQuiz(quizDto.getCategory(), quizDto.getNumOfQuestions(), quizDto.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<QuizResponse> responses) {
        return this.quizService.calculateScore(responses);
    }
}
