package com.app.QuizPro.controller;

import com.app.QuizPro.dto.QuizDto;
import com.app.QuizPro.model.Quiz;
import com.app.QuizPro.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
 public class HomeController {
    @Autowired
    private QuizService quizService;
   @GetMapping("")
    public String home (Model model){
        List<QuizDto> quizList= quizService.getAllQuiz();
        model.addAttribute("quizList", quizList);
        model.addAttribute("name", "QuizPro");

       return "index";

    }
}
