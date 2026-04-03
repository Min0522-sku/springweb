package pratice.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 엔티티 상속 용도 클래스
@Getter
@EntityListeners(AuditingEntityListener.class) // 해당 엔티티 변경감지 기능
public class BaseTime {
    @CreatedDate // 엔티티 생성날짜/시간 주입
    private LocalDateTime createDate;
    @LastModifiedDate // 엔티티 수정 날짜/시간 주입
    private LocalDateTime updateDate;
}
