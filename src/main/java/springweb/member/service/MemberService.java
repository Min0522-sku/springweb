package springweb.member.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springweb.member.dto.MemberDto;
import springweb.member.entity.MemberEntity;
import springweb.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //[*] 비크립트(암호화) 객체 생성
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // 1] 회원가입
    public boolean signup(MemberDto memberDto){
        // 1 저장할 dto --> entity 변환
        MemberEntity saveEntity = memberDto.toEntity();
        // ****************** 최종 저장 하기 전에 입력받은 비밀번호를 암호화 *****
        String pwd = passwordEncoder.encode(saveEntity.getMpwd()); // 입력받은 비밀번호를 암호화
        saveEntity.setMpwd(pwd); // 암호화된 비밀번호 저장
        // 2 jpa의 save 메소드
        MemberEntity saved = memberRepository.save(saveEntity);
        // 3 확인
        if (saved.getMno() > 0){return true;}
        return false;
    }

    // 2] 로그인
    public boolean login(MemberDto loginDto){
        // JPA으로 아이디로 엔티티찾기 SQL 로 아이디/비밀번호 일치 여부로 로그인 판단 불가능.
        Optional<MemberEntity> optionalMember = Optional.ofNullable(memberRepository.findByMid(loginDto.getMid()));
        if(optionalMember.isPresent()){
            MemberEntity memberEntity = optionalMember.get();
            // 비크립트 암호화로 평문과 암호화문 비교 passwordEncoder.matches(평문, 암호문);
            boolean result = passwordEncoder.matches(loginDto.getMpwd(), memberEntity.getMpwd());
            if (result == true){return true;} // 로그인성공
            else return false; // 로그인 실패 (패스워드 다를때)
        }
        return false; // 로그인 실패 (아이디 없을때)
    }
}

/*
    암호화
        1. 정의 : 자료를 보호 하기 위해 사람이 이해하기 어려운 데이터로 변환
        2. 목적 : 자료보호, 신뢰성, 무결성 유지
        3. 사용처 : 비밀번호, 금융, HTTPS 등등
        4. 용어 :
            - 평문 : 원래의 자료
            - 암호문 : 암호화된 자료
            - 암호화 : 평문 자료를 함호문으로 변환하는 과정
            - 복호화 : 암호문 자료를 평문으로 변환하는 과정
        `   - 단방향 암호화 : 평문을 암호문으로 변환하고 다시 평문으로 변환 불가능(복호화 없음)
            - 양방향 암호화 : 평문을 암호문으로 변환하고 다시 평문으로 변환 가능

            - 해시 함수 : HASH 자료를 고정된 길이로 변환 하는 함수
                * 서로 다른 자료들을 * 동일한 길이 * 로 변환하는 함수
                * 임의의 계산식으로 변환하는 과정이므로 다시 되돌리기 불가능 하다.
                - 자바 : .hashCode(), Object클래스의 메소드로 객체주소값을 해시코드(일정한 길이의 값)로 반환
                    예시) 실제로는 아님
                    - '사과' -->A1B2C3
                    - '사과' -->A1B2C3 * 같은 자료는 같은 해시값이 나올 수 있다.
                    - '바나나' --> X1C2V3 * 단 서로 다른 자료도 일정한 길이로 변환 한다.

            - 솔트 : 암호화 할때 사용되는 랜덤값 (동일한 계산식(알고리즘/해시)의 서로 다른 결과 값을 얻기위해)
                예시) 실제로는 아님
                - '사과' --> 솔트 추가 --> A1B2C5

        5. 종류
            1) 비밀번호 : Bcrypt(비크립트), 해시함수, 단반향/복호화없음,
            2) 전자서명/파일 : SHA-256 , 해시함수, 단방향/복호화없음,  SHA-512 뒤에 숫자는 비트수
            4) 웹통신 : HTTPS( TLS,SSL ), HTTP(암호화 안된 상태)

        6. 비크립트
            1) 설치 :
                1: 스프링 시큐리티에 포함
                2: 스프링 시큐리티의 비크립트만 사용
                    implementation 'org.springframework.security:spring-security-crypto:6.4.4'
            2) 사용법 :
                객체 생성 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                - String 암호화된 값 = 암호객체.encode(암호화할자료);
                - boolean 비교결과 = 암호객체.matches(평문, 암호문);

 */
