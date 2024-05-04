package com.app.QuizPro.dto;

import lombok.Data;

@Data
public class QuestionDto {


    Integer id;
    Integer quizId;

    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    String answer;



}


