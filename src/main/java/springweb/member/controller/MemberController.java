package springweb.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<?> login(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(memberService.login(memberDto));
    }
}
