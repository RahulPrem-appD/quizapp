package com.quizler.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizler.quizapp.model.Question;
import com.quizler.quizapp.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Question object cannot be null");
        }

        try{
            questionRepository.save(question);
            return new ResponseEntity<String>("Success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<String>("Failed to Add", HttpStatus.BAD_REQUEST);

    }
}
