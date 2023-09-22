package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ArticleApiController {


    @Autowired
    private ArticleService service;


    @GetMapping("/api/articles")
    public List<Article> index() {

        return service.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return service.show(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm form) {
        Article created = service.create(form);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form) {
//        Article article = form.toEntity();
//        Article target = repo.findById(id).orElse(null);
//        if (target == null || id != target.getId()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        Article updated = repo.save(article);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
        Article updated = service.update(id, form);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        Article article = repo.findById(id).orElse(null);
//        if (article == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        }
//        repo.deleteById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
        Article deleted = service.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createList = service.createArticles(dtos);
        return (createList != null) ? ResponseEntity.status(HttpStatus.OK).body(createList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}