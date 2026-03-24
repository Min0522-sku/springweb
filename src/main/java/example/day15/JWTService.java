package example.day15;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JWTService {
    //* 내가 만든 임의의 값으로 토큰에 사용되는 암호화 계산식의 비밀번호     (32글자 이상)
    private final String pwd = "123456789123456789123456789123456789";
    // * 내가 만든 임의의 값(비밀번호) 해시값(SHA)으로 변환
    private final Key pwdkey = Keys.hmacShaKeyFor(pwd.getBytes());
    // JWT 토큰 생성 : 사용자의 정보를 JSON 형식으로 암호화
    public String create(String data) {
        String token = Jwts.builder() // 토큰 생성시작
                //.claim("key", value)
                .claim("data", data)
                .setIssuedAt(new Date()) // 토큰 발급 날짜/시간,  new Date() : 시스템 날짜/시간 반환 객체
                // .setExpiration(new Date(System.currentTimeMillis()+1000 * 초단위))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60)) // 토큰 유지/유효 시간
                //.signWith(비밀키, 암호화알고리즘)
                .signWith(pwdkey, SignatureAlgorithm.HS256) // 최종 토큰 암호화는 HS256 알고리즘 적용
                .compact(); // 토큰 최종 문자열로 반환

        return token;
    }

    // JWT 토큰 값 추출
    public String read(String token){
        try {
            Claims claims = Jwts.parser() // 파싱
                    .setSigningKey(pwdkey) // 서명 인증에 필요한 비밀키 대입
                    .build() // 비밀키 확인
                    .parseClaimsJws(token) // 검증할 토큰 대입
                    .getBody(); // 검증 성공시 클레임(내용물) 가져온다.

            return (String) claims.get("data"); // 저장된 값은 무조건 Object 타입
        }catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

/*
    JWT : Json Web Token(징표)
        1. 정의 : JSON 형식의 데이터를 사용하기 위한 토큰 기반의 인증 형식
        2. 목적 : 웹/앱 에서 인증과 권한부여/확인 사용(클라이언트) vs 세션(서버)

    사용법
        1) 설치 :
            implementation 'io.jsonwebtoken:jjwt-api:0.12.6'    // jwt
            runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.6'
            runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.6'

 */