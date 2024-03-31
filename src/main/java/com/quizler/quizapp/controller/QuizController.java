package com.quizler.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.quizler.quizapp.model.QuestionDto;
import com.quizler.quizapp.model.SubmitRequest;
import com.quizler.quizapp.service.QuizService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz (@RequestParam String category, @RequestParam int count, @RequestParam String title){
        return quizService.createQuiz(category, count, title);
    }

    @GetMapping("getQuiz/{quizId}")
    public List<QuestionDto> getQuizQuestionsForUsers(@PathVariable int quizId) {
        return quizService.getQuizQuestionsForUsers(quizId) ;
    }

    @PostMapping("submit/{quizId}")
    public int submitAndGetScore(@RequestBody List<SubmitRequest> submitRequest, @PathVariable int quizId) {
        return quizService.submitAndGetScore(submitRequest, quizId);
    }
    
}
