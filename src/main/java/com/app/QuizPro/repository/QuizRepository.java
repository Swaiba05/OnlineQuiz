package com.app.QuizPro.repository;

import com.app.QuizPro.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository  extends JpaRepository<Quiz,Integer> {
}
