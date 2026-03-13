package example.day09.챕터6챕터7.service;

import example.day09.챕터6챕터7.repository.BlogRepository;
import example.day09.챕터6챕터7.dto.ArticleDto;
import example.day09.챕터6챕터7.entity.ArticleEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    @Autowired private final BlogRepository blogRepository;

    public ArticleEntity save(ArticleDto articleDto){
        return blogRepository.save(articleDto.toEntity());
    }

    public List<ArticleEntity> findAll(){
        return blogRepository.findAll();
    }

    public ArticleEntity findById(long id){
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public ArticleEntity update(long id, ArticleDto articleDto){
        ArticleEntity articleEntity = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));

        articleEntity.update(articleDto.getTitle(), articleDto.getContent());
        return articleEntity;
    }
}
