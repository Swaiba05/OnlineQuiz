package com.app.QuizPro.service;

import com.app.QuizPro.dto.QuizDto;
import com.app.QuizPro.model.Quiz;
import com.app.QuizPro.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    public Quiz save(QuizDto quizDto){
        Quiz quiz= new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setDescription(quizDto.getDescription());

        Quiz newQuiz= quizRepository.save(quiz);
        return newQuiz;
    }
    public List<QuizDto> getAllQuiz() {

        List<Quiz> quizList = quizRepository.findAll();

        List<QuizDto> quizDtoList = new ArrayList<>();
        for(Quiz quiz: quizList) {
            QuizDto quizDto = new QuizDto();
            quizDto.setId(quiz.getId());
            quizDto.setTitle(quiz.getTitle());
            quizDto.setDescription(quiz.getDescription());
            quizDtoList.add(quizDto);
        }

        return quizDtoList;
    }


}
