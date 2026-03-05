package example.day05.practice5;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
// dto 와 다른점 영속성
@Entity
@Table( name = "book")
public class BookEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer bno;
    @Column( name = "bname", nullable = false, length = 100)
    private String bname;
    @Column(nullable = false, length = 50) // name 생략시 자바 멤버변수명으로 자동 설정
    private String bauthor;
    @Column // nullable 생략시 true 자동 설정, length 생략시 자동으로 255 설정
    private String bpublisher;
}
// 데이터들의 실체 <--> 영속성