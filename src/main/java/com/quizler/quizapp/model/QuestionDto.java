package com.quizler.quizapp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDto {
    int id;
    String category;
    String questionTitle;
    String option1;
    String option2;
    String option3;
    String option4;
}
