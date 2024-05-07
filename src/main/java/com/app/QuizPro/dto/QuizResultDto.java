package com.app.QuizPro.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class QuizResultDto {

    Integer correctAnswerCount;
    Integer totalQuestion;
    BigDecimal percentage;
    String message;

}
