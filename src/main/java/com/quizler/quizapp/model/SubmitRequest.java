package com.quizler.quizapp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubmitRequest {

  int id;
  String Response;
}
