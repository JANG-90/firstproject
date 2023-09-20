package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController

public class ArticleApiController {
    @Autowired
    private ArticleRepository repo;


    @GetMapping("/api/articles")
    public List<Article> index() {
        return repo.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return repo.findById(id).orElse(null);
    }

    @PostMapping("/api/articles")
    public Article create (@RequestBody ArticleForm form){
        Article aricle = form.toEntity();
        return repo.save(aricle);

    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form){
        Article article = form.toEntity();
        Article target = repo.findById(id).orElse(null);
        if (target==null||id!=target.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Article updated = repo.save(article);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }


}
