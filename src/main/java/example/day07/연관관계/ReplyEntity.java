package example.day07.연관관계;

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
@Table(name = "reply")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rno;
    private String rcontent;

    // 단반향 FK
    @ManyToOne(cascade = CascadeType.PERSIST) // FK 제약조건 설정
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;
}
