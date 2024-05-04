package com.app.QuizPro.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    String answer;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


}
