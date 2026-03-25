package springweb.member.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor

public class JWTService {

    // 비밀키 정의
    private final String pwd = "123456789123456789123456789123456789";
    private final Key pwdkey = Keys.hmacShaKeyFor(pwd.getBytes()); // getBytes(StandardCharsets.UTF_8) 비밀키에 한글 가능

    // 토큰 발급 : 특정한 자료를 이해하기 어려운 자료로 변경
    public String createToken(String mid){
        String token = Jwts.builder() // 토큰객체 생성 빌더 시작
                .claim("mid", mid) // key와 value 쌍으로 토큰에 저장할 값(여러개가능)
                .setIssuedAt(new Date())// 토큰 발급 날짜/시간
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*60)) // 토큰 유효 기간
                .signWith(pwdkey, SignatureAlgorithm.HS256)
                .compact(); // 토큰 최종 문자열 반환

        return token;
    }


    // 토큰의 클레임(내용물) 추출
    public String getClaim(String token){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(pwdkey) // 비밀키 대입 (불일치시 예외 발생)
                    .build()
                    .parseClaimsJws(token) // 서명확인활 토큰 대입
                    .getBody(); // 서명 확인 토큰내 클래임(내용물) 반환/ 없으면 예외 발생
            return (String) claims.get("mid");
        }
        catch (Exception e){System.out.println(e);}
        return null;
    }

}
