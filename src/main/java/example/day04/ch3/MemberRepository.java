package example.day04.ch3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 1. 인터페이스 위에 @Repository를 붙인다
    // 2. JpaRepository 로부터 상속받는다
    // 3. JpaRepository< 매핑클래스명, pk타입 > 지정한다
    // * < > 제너릭타입
}
