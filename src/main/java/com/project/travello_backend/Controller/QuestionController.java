package com.project.travello_backend.Controller;


import com.project.travello_backend.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {


    @Autowired
    private QuestionService questionservice;


    @PostMapping("/addkeyword")
    public void addkeyword(@RequestBody String keyword){
        questionservice.addKeyword(keyword);
    }

    @PostMapping("/searchquestion")
    public String searchquestion(@RequestBody String keyword){
        return questionservice.searchQuestion(keyword);
    }

    @PostMapping("/adddestination")
    public void adddestination(@RequestBody String keyword){
        questionservice.addDestinations(keyword);
    }
}
