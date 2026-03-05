package example.day06.practice6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieDto {
    private Integer movieid;
    private String title;
    private String director;
    private String releasedate;
    private Integer rating;

    private String createDate;
    private String updateDate;

    public MovieEntity toEntity(){
        // 빌더 패턴
        // this 해당 메소드/함수 실행한 객체

        return MovieEntity.builder() // 빌더 시작
                .movieid(this.movieid)
                .title(this.title)
                .director(this.director)
                .releasedate(this.releasedate)
                .rating(this.rating)
                .build(); // 빌더 끝
    }
}
