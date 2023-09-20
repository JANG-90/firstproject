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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller

public class ArticleController {

    @GetMapping("/articles")
    public String index(Model model) {
        ArrayList<Article> articleEntityList = repo.findAll();
        model.addAttribute("articleList", articleEntityList);
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
        return "redirect:/articles/" + saved.getId();
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

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        Article target = repo.findById(articleEntity.getId()).orElse(null);
        if (target != null) {
            repo.save(articleEntity);
        }
        return "redirect:/articles/"+articleEntity.getId();//redirect 다시 한번 유알엘 다른컨트롤부르기위해
    }

    @GetMapping("/articles/{id}/delete")//redirect 다시 한번 유알엘 다른컨트롤부르기위해
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("Delete");
        Article target = repo.findById(id).orElse(null);
        log.info(target.toString());
        if (target!=null){
            repo.delete(target);
            rttr.addFlashAttribute("msg","게시물이 삭제됐습니다.");
        }
        return "redirect:/articles";
    }

}