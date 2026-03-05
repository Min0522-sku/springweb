package example.day04.ch3;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 빈생성자 이면서 protected 접근제한
@AllArgsConstructor // 전체 매개변수를 갖는 생성자
@Getter // getter 메소드 자동생성
@Setter
@Entity // 데이터 베이스의 테이블 레코드와 매핑(연결) 기술: ORM(자바객체 <--> DB레코드)
public class Member {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}
