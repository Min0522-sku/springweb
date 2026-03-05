package example.day06.entity;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass // 엔티티 상속 용도 클래스
public class BaseTime {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
