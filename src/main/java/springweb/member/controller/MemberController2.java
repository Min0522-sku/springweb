package springweb.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.member.dto.MemberDto;
import springweb.member.service.JWTService;
import springweb.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member2")
@CrossOrigin(value = "http://localhost:5173", exposedHeaders = "Authorization")
public class MemberController2 {
    private final MemberService memberService;
    private final JWTService jwtService;
    // 1] 회원가입 post = create = save
    // http://localhost:8080/api/member2/signup  { "mid": "qwer",  "mpwd" : "qwer", "mname" : "닉네임"}
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(memberService.signup(memberDto));
    }


    // 2] 로그인 세션방식 --> 토큰방식
    // http://localhost:8080/api/member2/login  { "mid": "user01",  "mpwd" : "1234" }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto loginDto){
        // 입력받은 아이디/비밀번호를 서비스에게 보낸다
        boolean result = memberService.login(loginDto);
        // 로그인 성공이면 토큰 발급
        if (result){
            String token = jwtService.createToken(loginDto.getMid()); //
            return ResponseEntity.ok()
            // 토큰은 주로 세션과 다르게 서버에 자장하지 않고 클라이언트에 저장한다.
                    .header("Authorization", "Bearer "+token) // HTTP 통신의 부가정보 담는 구역(주로 인증정보 포함)
                    // 클라이언트에게 헤더에 발급받은 JWT토큰 반환. "Bearer " 띄어쓰기 주의
                    .body(true);

        }
        // 아니면 로그인 실패
        return ResponseEntity.ok(false);
    }

    // 3] 로그아웃  세션방식 ---> 토큰방식 변경
    // 서버가 token 저장하고 있지 않기 때문에 통신이 필요없다.
    // 즉] 클라이언트 측에서 token 제거하면 된다.


    // 4] 마이페이지 세션방식 --> 토큰방식
    @GetMapping("/myinfo")
    public ResponseEntity<?> myinfo(@RequestHeader("Authorization") String token ){
        // @RequestHeader : http 요청의 header 정보 매핑
        // @RequestHeader("Authorization") String token 매개변수로 받는다
        //  만약에 헤더가 없거나 토큰이 없으면 비로그인
        if (token == null || !token.startsWith("Bearer")){
            return ResponseEntity.ok(false);
        }
        // 토큰 추출
        token =  token.replace("Bearer ", ""); // Bearer 없애기 띄어쓰기 주의

        String mid = jwtService.getClaim(token);
        if (mid == null) return ResponseEntity.ok(false);

        return ResponseEntity.ok(memberService.myinfo(mid));
    }

}
