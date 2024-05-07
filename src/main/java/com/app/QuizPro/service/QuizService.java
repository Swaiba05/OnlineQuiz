package com.app.QuizPro.service;

import com.app.QuizPro.dto.QuizDto;
import com.app.QuizPro.dto.QuizResultDto;
import com.app.QuizPro.dto.ResultDto;
import com.app.QuizPro.model.Question;
import com.app.QuizPro.model.Quiz;
import com.app.QuizPro.repository.QuestionRepository;
import com.app.QuizPro.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;
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

    public QuizResultDto submitQuiz(List<ResultDto> resultDtos, Integer id) {
        //fetch all questions based on current quiz
        List<Question> questionList = questionRepository.findAllByQuizId(id);
        int count = 0;

        for (ResultDto resultDto : resultDtos) {
            Question question = questionRepository.findById(resultDto.getId()).orElse(null);
            if (question != null) {
                if (question.getAnswer().equals(resultDto.getAnswer())) {
                    count++;
                }
            }
        }
        QuizResultDto quizResultDto = new QuizResultDto();
        quizResultDto.setCorrectAnswerCount(count);
        quizResultDto.setTotalQuestion(resultDtos.size());
        quizResultDto.setMessage("Congratulations!");


        if (count > 0) {
            double percentage = (double) count / resultDtos.size();
            percentage = percentage*100; //16.67243
            BigDecimal roundedResult = new BigDecimal(percentage).setScale(2, RoundingMode.HALF_UP);

            quizResultDto.setPercentage(roundedResult);
        } else {
            quizResultDto.setPercentage(new BigDecimal("0.00"));
        }
        return quizResultDto;

    }


}
