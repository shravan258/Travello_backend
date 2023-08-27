package com.project.travello_backend.Entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="question_keywords")
public class QuestionKeywords {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ElementCollection
    private List<String> keywordss = new ArrayList<>();

    public QuestionKeywords() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getKeywordss() {
        return keywordss;
    }

    public void setKeywordss(List<String> keywordss) {
        this.keywordss = keywordss;
    }

    @Override
    public String toString() {
        return "QuestionKeywords{" +
                "id=" + id +
                ", keywordss=" + keywordss +
                '}';
    }

    public void addKeyword(String keyword){
        keywordss.add(keyword);
    }
}
