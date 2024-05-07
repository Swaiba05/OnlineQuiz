package com.app.QuizPro.controller;

import com.app.QuizPro.dto.QuestionDto;
import com.app.QuizPro.dto.QuizDto;
import com.app.QuizPro.dto.QuizResultDto;
import com.app.QuizPro.dto.ResultDto;
import com.app.QuizPro.service.QuestionService;
import com.app.QuizPro.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    @PostMapping("submit/{id}")
    private ResponseEntity<QuizResultDto> submitQuiz(@RequestBody List<ResultDto> result, @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(quizService.submitQuiz(result, id), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    @GetMapping("result")
    private String finalResult(Model model, @RequestParam BigDecimal score) {
        model.addAttribute("score", score + "%");
        return "result";
    }
    @GetMapping("{id}")
    private String createQuestion(Model model, @PathVariable Integer id) {
        // define a html paidge that returns question creation page
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuizId(id);
        model.addAttribute("questionDto",questionDto);
        return "create_question";

    }
}


