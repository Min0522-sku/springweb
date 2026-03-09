package example.day07.연관관계;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;
    private String bcontent;

    // 단반향 FK 만들기
    // @JoinColumn 관례적으로 fk 필드명도 pk 필드명과 동일
    // @ManyToOne 다수가 하나에게, 1:M 하나의 카테고리에 여러게시물 참조
    @ManyToOne
    @JoinColumn(name = "cno")
    private CategoryEntity categoryEntity;

    // ** 양방향 **
    @OneToMany( mappedBy = "boardEntity")
    @ToString.Exclude @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();
}



