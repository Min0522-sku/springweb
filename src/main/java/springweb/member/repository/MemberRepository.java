package springweb.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springweb.member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 아이디로 엔티티 찾기
    // findBy필드명(값) : 필드명 반드시 카멜케이스
    MemberEntity findByMid(String mid);
}
