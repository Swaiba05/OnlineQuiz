package com.app.QuizPro.controller;


import com.app.QuizPro.dto.QuestionDto;
import com.app.QuizPro.model.Question;
import com.app.QuizPro.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("")
    private String createQuestion(Model model) {
        // define a html page that returns question creation page
        QuestionDto questionDto = new QuestionDto();
        model.addAttribute("questionDto",questionDto);
        return "create_question";

    }
//question/add

    @PostMapping("add")
    private String addQuestion(QuestionDto questionDto){
        System.out.println(questionDto);
        questionService.save(questionDto);
        return "redirect:list";

    }
    @PostMapping("add/{quizId}")
    private String save(QuestionDto questionDto, @PathVariable Integer quizId) {
        questionService.save(questionDto);
        return "redirect:/quiz/list";
    }

    @GetMapping("list")
    private String getQuestions(Model model) {

        // fetch all questions
        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("questionList",questionList);
        //show in the UI
        return "question_list";
    }
}
