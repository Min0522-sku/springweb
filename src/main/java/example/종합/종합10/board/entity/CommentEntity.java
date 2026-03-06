package example.종합.종합10.board.entity;

import example.종합.종합10.board.dto.CommentDto;
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
@Table(name = "comment")
public class CommentEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cno;
    @Column(columnDefinition = "longtext not null")
    private String ccontent;
    @Column(length = 30, nullable = false)
    private String cwriter;
    @Column(nullable = false)
    private Integer bno;

    public CommentDto toDto(){
        return CommentDto.builder()
                .cno(cno)
                .ccontent(ccontent)
                .cwriter(cwriter)
                .bno(bno)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }
}
