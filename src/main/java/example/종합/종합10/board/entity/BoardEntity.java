package example.종합.종합10.board.entity;

import example.종합.종합10.board.dto.BoardDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data@Builder
@Entity@Table(name = "board")
public class BoardEntity extends BaseTime{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY) // auto_increment
    private Integer bno;
    @Column(nullable = false) // not null
    private String btitle;
    @Column(columnDefinition = "longtext not null") // sql 작성
    private String bcontent;
    @Column(length = 30, nullable = false)
    private String bwriter;

    public BoardDto toDto(){
        return  BoardDto.builder()
                .bno(bno)
                .bcontent(bcontent)
                .bwriter(bwriter)
                .btitle(btitle)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }
}
