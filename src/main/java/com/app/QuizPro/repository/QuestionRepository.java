package com.app.QuizPro.repository;

import com.app.QuizPro.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findAllByQuizId(Integer quizId);
}
