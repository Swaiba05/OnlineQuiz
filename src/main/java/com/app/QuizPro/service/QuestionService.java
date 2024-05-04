package com.app.QuizPro.service;

import com.app.QuizPro.dto.QuestionDto;
import com.app.QuizPro.model.Question;
import com.app.QuizPro.model.Quiz;
import com.app.QuizPro.repository.QuestionRepository;
import com.app.QuizPro.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizRepository quizRepository;
    public Question save(QuestionDto questionDto) {
        // to save data
        Question question=new Question();
        question.setQuestion(questionDto.getQuestion());
        question.setOption1(questionDto.getOption1());
        question.setOption2(questionDto.getOption2());
        question.setOption3(questionDto.getOption3());
        question.setOption4(questionDto.getOption4());
        question.setAnswer(questionDto.getAnswer());
        Quiz quiz = quizRepository.findById(questionDto.getQuizId()).orElse(null);
        if (quiz != null) {
            question.setQuiz(quiz);
        }

       Question newQuestion= questionRepository.save(question);
       return newQuestion;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    public List<QuestionDto> getAllQuestionsByQuiz(Integer quizId) {
        Quiz  quiz= quizRepository.findById(quizId).orElse(null);
        List<Question> questionList = new ArrayList<>();
        if(quiz!=null) {
            questionList = questionRepository.findAllByQuizId(quizId);
        }

        List<QuestionDto>  questionDtos = new ArrayList<>();
        for(Question question: questionList){
            QuestionDto questionDto= new QuestionDto();
            questionDto.setId(question.getId());
            questionDto.setQuestion(question.getQuestion());
            questionDto.setOption1(question.getOption1());
            questionDto.setOption2(question.getOption2());
            questionDto.setOption3(question.getOption3());
            questionDto.setOption4(question.getOption4());
            questionDto.setAnswer(question.getAnswer());
            questionDtos.add(questionDto);
        }
        return questionDtos;

    }

}
