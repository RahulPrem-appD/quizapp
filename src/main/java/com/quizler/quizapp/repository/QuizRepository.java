package com.quizler.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizler.quizapp.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
