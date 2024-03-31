package com.quizler.quizapp.service;

import com.quizler.quizapp.model.Question;
import com.quizler.quizapp.model.QuestionDto;
import com.quizler.quizapp.model.Quiz;
import com.quizler.quizapp.model.SubmitRequest;
import com.quizler.quizapp.repository.QuestionRepository;
import com.quizler.quizapp.repository.QuizRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(
            String category,
            int count,
            String title) {
        List<Question> questions = questionRepository.findByCategoryWithCount(
                category,
                count);

        Quiz quiz = new Quiz();
        quiz.setQuestions(questions);
        quiz.setTitle(title);

        quizRepository.save(quiz);

        return ResponseEntity.ok("Successfully created");
    }

    public List<Question> getQuizQuestions(int quizId) {
        Quiz quiz = quizRepository
                .findById(quizId)
                .orElseThrow(() -> new InvalidDataAccessApiUsageException("No Quiz present for this entry"));
        return quiz.getQuestions();
    }

    public List<QuestionDto> getQuizQuestionsForUsers(int quizId) {
        return this.getQuizQuestions(quizId)
                .stream()
                .map(question -> Optional
                        .ofNullable(question)
                        .map(q -> {
                            QuestionDto questionDto = new QuestionDto();
                            BeanUtils.copyProperties(q, questionDto);
                            return questionDto;
                        })
                        .orElseThrow(() -> new IllegalArgumentException("Question object cannot be null")))
                .collect(Collectors.toList());
    }

    public int submitAndGetScore(List<SubmitRequest> submitRequest, int quizId) {
        List<Question> questions = this.getQuizQuestions(quizId);

        int i = 0;
        int score = 0;
        for (SubmitRequest answer : submitRequest) {
            if (answer.getResponse().equals(questions.get(i).getRightAnswer())) {
                score++;
            }
            i++;
        }
        return score;
    }
}
