package com.project.travello_backend.Services;

public interface QuestionService {

    public void addKeyword(String keyword);

    public String searchQuestion(String question);

    public void addDestinations(String destination);
}
