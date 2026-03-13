package example.day09.챕터6챕터7.controller;

import example.day09.챕터6챕터7.dto.ArticleDto;
import example.day09.챕터6챕터7.service.BlogService;
import example.day09.챕터6챕터7.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {
    @Autowired private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleEntity> addArticle(@RequestBody ArticleDto articleDto){
        ArticleEntity savedEntity = blogService.save(articleDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedEntity);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleDto>> findAllArticles(){
        List<ArticleDto> articles = blogService.findAll()
                .stream()
                .map(ArticleDto::new)
                .toList();
        return ResponseEntity.ok().body(articles);
    }
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDto> findArticle(@PathVariable long id){
        ArticleEntity articleEntity = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleDto(articleEntity));
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleEntity> updateArticle(@PathVariable long id, @RequestBody ArticleDto articleDto){
        ArticleEntity updateEntity = blogService.update(id, articleDto);

        return ResponseEntity.ok().body(updateEntity);
    }
}
