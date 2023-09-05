package com.marc.service.impl;

import com.marc.dto.QuestionWrapper;
import com.marc.dto.QuizResponse;
import com.marc.entities.Quiz;
import com.marc.fein.QuizInterface;
import com.marc.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceImpl {
    @Autowired
    private QuizRepository quizrepository;
    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        this.quizrepository.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = quizrepository.findById(id).orElseThrow(()->new RuntimeException());

        List<Integer> questionIds = quiz.getQuestions();

        List<QuestionWrapper> questions = quizInterface.getQuestionsFromId(questionIds).getBody();

        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<QuizResponse> responses) {
        ResponseEntity<Integer> submittedQuiz = quizInterface.submitQuiz(responses);
        return submittedQuiz;
    }
}
