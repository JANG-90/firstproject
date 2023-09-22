package com.example.firstproject.dto;


import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor  //생성자 자동생성
@ToString
@Getter
public class ArticleForm {


    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }

}
