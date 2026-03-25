package springweb.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.member.dto.MemberDto;
import springweb.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    // 1] 회원가입 post = create = save
    // http://localhost:8080/api/member/signup  { "mid": "qwer",  "mpwd" : "qwer", "mname" : "닉네임"}
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    // 2] 로그인 post = select = find
    // http://localhost:8080/api/member/login  { "mid": "user01",  "mpwd" : "1234" }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto loginDto, HttpSession session){

        // 입력받은 아이디/비밀번호를 서비스에게 보낸다
        boolean result = memberService.login(loginDto);
        // 로그인 성공이면 세션 부여
            if (result) {
                // 매개변수에 HttpSession session 받는다
                // 로그인 성공한 회원의 아이디를 세션객체내 저장 .setAttribute("속성명", 속성값); 세션 저장
                session.setAttribute("loginMid", loginDto.getMid()); // 서버에 저장하기 때문에 서버 과부화를 예방하기위해 가벼운 속성을 저장하는게 좋음
            }
            // 아니면 실패
        return ResponseEntity.ok(result);
    }

    // 3] 로그아웃 get = 세션초기화
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        // 매개변수에 HttpSession session 받는다
        // 특정한 속성명으로 세션객체내 속성 삭제, session.removeAttribute("삭제할속성명");  세션 삭제
        session.removeAttribute("loginMid");
        // 반환
        return  ResponseEntity.ok(true);
    }

    // 4] 마이페이지 get = 현재로그인된 회원정보 = 세션에 저장된 정보로 조회
    @GetMapping("/myinfo")
    public ResponseEntity<?> myinfo(HttpSession session){
        // 로그인 상태 꺼내기 * 모든 세션객체내 속성갑은 Object 타입이다*  세션 조회
        Object obj = session.getAttribute("loginMid");
        // 로그인 상태 존재하지 않으면 비로그인상태
        if (obj == null){return ResponseEntity.ok(false);}
        // 로그인 상태이면, 다운캐스팅 Object --> String
        String loginMid = (String) obj;
        // 로그인된 mid로 서비스에게 전달하여 로그인된 mid의 dto 받아오기
        return ResponseEntity.ok(memberService.myinfo(loginMid));
    }

}
