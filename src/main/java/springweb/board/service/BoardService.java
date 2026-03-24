package springweb.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springweb.board.dto.BoardDto;
import springweb.board.entity.BoardEntity;
import springweb.board.repository.BoardRepository;
import springweb.member.entity.MemberEntity;
import springweb.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 글쓰기
    public boolean write(BoardDto boardDto, String loginMid){
        // dto --> entity
        BoardEntity saveEntity = boardDto.toEntity();
        // *** 저장전에 FK 대입하기, FK의 엔티티를 찾아서 대입*****
        // 현재 로그인된 mid로 엔티티 찾기
        Optional<MemberEntity> entityOptional = memberRepository.findByMid(loginMid);
        if (!entityOptional.isPresent()){ // ! 부정문
            return false; // 존재하지 않은 회원 : 실패
        }
        // 저장할 게시물 엔티티에 set참조엔티티(회원엔티티);
        saveEntity.setMemberEntity(entityOptional.get());

        BoardEntity saved = boardRepository.save(saveEntity);
        if (saved.getBno()>0){return true;}
        else return false;
    }

}
