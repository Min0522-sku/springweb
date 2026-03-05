package example.day06.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GoodsDto {
    private Integer gno;
    private String gname;
    private Integer gprice;
    private String gdesc;

    // basetime
    private String createDate;
    private String updateDate;

    // ** DTO --> ENTITY 변환 함수

    public GoodsEntity toEntity(){
        // 빌더 패턴
        // this 해당 메소드/함수 실행한 객체

        return GoodsEntity.builder() // 빌더 시작
                .gno(this.gno)
                .gname(this.gname)
                .gprice(this.gprice)
                .gdesc(this.gdesc)
                .build(); // 빌더 끝
    }
}
