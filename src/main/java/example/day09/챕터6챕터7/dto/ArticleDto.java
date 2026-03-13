package example.day09.챕터6챕터7.dto;

import example.day09.챕터6챕터7.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor@NoArgsConstructor@Data@Builder
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleDto(ArticleEntity articleEntity){
        this.id = articleEntity.getId();
        this.title = articleEntity.getTitle();
        this.content = articleEntity.getContent();
        this.createdAt = articleEntity.getCreatedAt();
    }

    public  ArticleEntity toEntity(){
        return ArticleEntity.builder()
                .id(this.id)
                .title(title)
                .content(content)
                .build();
    }
}
