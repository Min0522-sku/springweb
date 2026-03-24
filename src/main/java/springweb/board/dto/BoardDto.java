package springweb.board.dto;

import lombok.*;
import springweb.board.entity.BoardEntity;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoardDto {

    private Long bno;

    private String btitle;

    private String bcontent;

    private String bfile;
    // DTO에는 엔티티 정보를 포함하지 않고 필요한 정보만 멤버변수 구성
    private Long mno;
    private String mname;


    private String createDate;
    private String updateDate;

    //
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .btitle(btitle)
                .bcontent(bcontent)
                .bfile(bfile)
                //.memberEntity() fk는 서비스에서 대입
                .build();
    }
}
