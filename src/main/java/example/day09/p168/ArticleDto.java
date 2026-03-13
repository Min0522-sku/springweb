package example.day09.p168;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data@Builder
public class ArticleDto {
    private String title;
    private String content;

    public ArticleDto(ArticleEntity articleEntity) {
        this.title = articleEntity.getTitle();;
        this.content = articleEntity.getContent();
    }

    public  ArticleEntity toEntity(){
        return ArticleEntity.builder()
                .title(title)
                .content(content)
                .build();
    }
}
