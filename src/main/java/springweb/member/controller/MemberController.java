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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto loginDto, HttpSession session){

        // 입력받은 아이디/비밀번호를 서비스에게 보낸다
        boolean result = memberService.login(loginDto);
        // 로그인 성공이면 세션 부여
            // 매개변수에 HttpSession session 받는다
            // 로그인 성공한 회원의 아이디를 세션객체내 저장 .setAttribute("속성명", 속성값);
            session.setAttribute("loginMid", loginDto.getMid()); // 서버에 저장하기 때문에 서버 과부화를 예방하기위해 가벼운 속성을 저장하는게 좋음
        // 아니면 실패
        return ResponseEntity.ok(result);
    }

    // 3] 로그아웃 get = 세션초기화
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        // 매개변수에 HttpSession session 받는다
        // 특정한 속성명으로 세션객체내 속성 삭제, session.removeAttribute("삭제할속성명");
        session.removeAttribute("loginMid");
        // 반환
        return  ResponseEntity.ok(true);
    }

}
