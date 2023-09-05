package com.marc.fein;

import com.marc.dto.QuestionWrapper;
import com.marc.dto.QuizResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("api/v1/question/generate")
    ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ);

    @PostMapping("api/v1/question/getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("api/v1/question/getScore")
    ResponseEntity<Integer> submitQuiz(@RequestBody List<QuizResponse> responses);

}
