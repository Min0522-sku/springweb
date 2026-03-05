package example.day06.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor
@Data@Builder

@Entity
@Table(name = "goods") // 생략시 테이블명이 클래스명으로 자동생성
public class GoodsEntity extends BaseTime{
    @Id // primary key
    @GeneratedValue( strategy = GenerationType.IDENTITY  ) //auto_increment
    private Integer gno;

    @Column(nullable = false, length = 100, unique = true, insertable = true, updatable = true)
    // nullable = not null 기본값은 true
    //unique 중복여부 기본값은 false , insertable insert할때 적용여부 기본값은 true, updatable update할때 적용여부 기본값은 true
    private String gname;
    // @Column 생략시 : 자바의 타입 --> SQL 타입, 자바의 변수명 --> SQL 필드명
    private Integer gprice;

    @Column( columnDefinition = "varchar(100) default '제품설명' not null") // JPA가 아닌 네이티브 실제 sql 쿼리 작성
    private String gdesc;



    // ** ENTITY --> DTO 변환 함수
    public GoodsDto toDto(){
        return GoodsDto.builder()
                .gno(gno)
                .gname(gname)
                .gdesc(gdesc)
                .gprice(gprice)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())// BaseTime의 멤버변수가 private라 getter setter를 사용해야함
                .build();
    }
}
