package example.종합.종합10.board.dto;


import example.종합.종합10.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class BoardDto {
    private Integer bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private String createDate;
    private String updateDate;


    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .btitle(this.btitle)
                .bwriter(this.bwriter)
                .bcontent(this.bcontent)
                //나머지는 자동이라 생략 저장이기 때문
                .build();
    }
}
