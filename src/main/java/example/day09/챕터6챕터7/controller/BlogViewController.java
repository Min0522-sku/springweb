package example.day09.챕터6챕터7.controller;

import example.day09.챕터6챕터7.dto.ArticleDto;
import example.day09.챕터6챕터7.entity.ArticleEntity;
import example.day09.챕터6챕터7.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    @Autowired private BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleDto> articleDtos = blogService.findAll().stream()
                .map(ArticleDto::new).toList();
        model.addAttribute("articles", articleDtos);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        ArticleEntity articleEntity = blogService.findById(id);
        model.addAttribute("article", new ArticleDto(articleEntity));
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null){
            model.addAttribute("article", new ArticleDto());
        } else {
            ArticleEntity articleEntity = blogService.findById(id);
            model.addAttribute("article", new ArticleDto(articleEntity));
        }

        return "newArticle";
    }
}
