package com.project.travello_backend.Dao;

import com.project.travello_backend.Entity.QuestionKeywords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KeywordDao extends JpaRepository<QuestionKeywords, Integer> {

    public String findByKeywordss(String keyword);
}
