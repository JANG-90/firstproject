package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repo;


    public List<Article> index() {
        return repo.findAll();
    }

    public Article show(Long id) {
        return repo.findById(id).orElse(null);

    }

    public Article create(ArticleForm form) {
        Article article = form.toEntity();
        if (form.getId() != null) {
            return null;
        }
        return repo.save(article);
    }

    public Article update(Long id, ArticleForm form) {
        Article article = form.toEntity();
        Article target = repo.findById(id).orElse(null);
        if (target == null || id != form.getId()) {
            log.info("잘못된 요청입니다. id: {}, article: {}", id, article.toString());
            return null;
        }
        log.info("수정 완료. id: {}, article: {}", id, article.toString());
        return repo.save(article);
    }

    public Article delete(Long id) {
        Article target = repo.findById(id).orElse(null);
        if (target == null) {
            log.info("삭제할 데이터가 존재하지 않습니다.id = {}", id);
            return null;
        }
        repo.delete(target);
        log.info("데이터가 삭제되었습니다. id= {}, article={}", id, target);
        return target;
    }

    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> repo.save(article));

        // 3. 강제 예외 발생시키기
        repo.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));

        // 4. 결과 값 반환하기
        return articleList;
    }

}
