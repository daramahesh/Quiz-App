package com.marcc.service.impl;

import com.marcc.dto.QuestionWrapper;
import com.marcc.dto.ResponseDto;
import com.marcc.entities.Question;
import com.marcc.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl {
    @Autowired
    private QuestionRepository repository;
    public void addQuestion(Question question) {
        this.repository.save(question);
    }

    public List<Question> getAllQuestions() {
        return this.repository.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return this.repository.findByCategory(category);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQ) {
        List<Integer> questions = this.repository.findRandomByCategory(category, numQ);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrapperList = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for (Integer integer:questionIds) {
            Question question = this.repository.findById(integer).get();
            questions.add(question);
        }
        for(Question question : questions) {
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setQuestionTitle(question.getQuestionTitle());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());
            wrapperList.add(questionWrapper);
        }
        return new ResponseEntity<>(wrapperList,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<ResponseDto> responses) {

        int right = 0;

        for(ResponseDto response:responses) {
            Question question = this.repository.findById(response.getId()).get();

            if(response.getResponse().equals(question.getRightAnswer())) {
                right+=5;
            }
            if(!response.getResponse().equals(question.getRightAnswer())) {
                right--;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
