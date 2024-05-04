package com.app.QuizPro.controller;

import com.app.QuizPro.dto.QuestionDto;
import com.app.QuizPro.dto.QuizDto;
import com.app.QuizPro.service.QuestionService;
import com.app.QuizPro.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("quiz")
public class QuizController {
@Autowired
private QuizService quizService;
    @Autowired
    private QuestionService questionService;
    @GetMapping("")

    private String getQuizList(Model model)
    {
        return   "quiz_list";
    }
    @GetMapping("create")
    private String createQuiz(Model model) {
        QuizDto quizDto = new QuizDto();
        model.addAttribute("quizDto", quizDto);
        return "create-quiz";
    }
    @PostMapping("add")
    private String save(QuizDto quizDto) {
        quizService.save(quizDto);
        return "redirect:list";

    }
    @GetMapping("list")
    private String getAll(Model model) {
        List<QuizDto> quizList = quizService.getAllQuiz();
        model.addAttribute("quizList", quizList);
        return "quiz-list";
    }
    @GetMapping("start/{id}")
    private String getQuiz(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("quizId",id);
        return "quiz";
    }
    @GetMapping("list/{id}")

    private ResponseEntity<List<QuestionDto>> getQuizList(@PathVariable("id") Integer id) {
        List<QuestionDto> questionDtoList = questionService.getAllQuestionsByQuiz(id);
        try {
            return new ResponseEntity<>(questionDtoList, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}


