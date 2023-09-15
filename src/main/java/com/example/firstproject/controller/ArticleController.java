package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller

public class ArticleController {

    @GetMapping("/articles")

    public String index(Model model){
        ArrayList<Article> articleEntityList = repo.findAll();
        model.addAttribute("articleList",articleEntityList);
        return "/articles/index";
    }

    @Autowired
    private ArticleRepository repo;

    @GetMapping("/articles/new")
    public String newArticleForm() {

        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
        Article article = form.toEntity();

        Article saved = repo.save(article);

        log.info(repo.findById(2L).toString());

        return "redirect:/articles/" +  saved.getId();

    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        Article articleEntity = repo.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);

        return "articles/show";

    }


    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article article = repo.findById(id).orElse(null);
        model.addAttribute("article", article);
        return "articles/edit";
    }
}