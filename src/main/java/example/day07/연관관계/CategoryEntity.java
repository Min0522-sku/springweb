package example.day07.연관관계;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor@NoArgsConstructor@Data@Builder

@Entity@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer cno;
    private String cname;
    // 양방향 데이터베이스에는 존재 안함
    // 자바에서만 조회용도로 사용, mappedBy = "매핑할 필드명"
    @OneToMany(mappedBy = "categoryEntity")
    @ToString.Exclude // 순환 참조 방지
    @Builder.Default // new 생성자 대신에 빌더 객체 생성시 초기값 사용
    private List<BoardEntity> boardEntityList = new ArrayList<>();
}
