package example.day06.practice6;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "movie")
public class MovieEntity extends BaseTime{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY  )
    private Integer movieid;

    private String title;
    private String director;
    private String releasedate;
    private Integer rating;

    public MovieDto toDto(){
        return MovieDto.builder()
                .movieid(movieid)
                .title(title)
                .director(director)
                .releasedate(releasedate)
                .rating(rating)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }

}
