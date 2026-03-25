package springweb.board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.board.dto.BoardDto;
import springweb.board.service.BoardService;
import springweb.member.service.JWTService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;
    private final JWTService jwtService;

    // 회원제 글 등록 + 세션 정보
    // http://localhost:8080/api/board/write { "btitle" : "테스트제목", "bcontent" : "테스트내용"}
    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BoardDto boardDto, HttpSession session){
        // 세션내 로그인 정보 확인
        Object obj = session.getAttribute("loginMid");
        if (obj == null){return ResponseEntity.ok(false);} // 비로그인이면 글쓰기 실패
        // 로그인중
        String loginMid = (String) obj;
        // 서비스에 입력받은 값과 세션에 저장된 값 전달
        boolean result = boardService.write(boardDto, loginMid);
        return ResponseEntity.ok(result);
    }

    // 회원제 글 등록 + 토큰
    @PostMapping("/write2")
    public ResponseEntity<?> write2(@RequestBody BoardDto boardDto , @RequestHeader("Authorization") String token){
        // 매개변수로 jwt 토큰 받는다
        // 토큰이 없거나 || 문자열.startsWith("시작문자") : 문자열내 시작문자가 존재하면 true 인데 앞에 !
        if (token == null || !token.startsWith("Bearer")){
            return ResponseEntity.ok(false);
        }
        // 토큰 추출
        token =  token.replace("Bearer ", "");
        String loginMid = jwtService.getClaim(token);
        if (loginMid == null){return ResponseEntity.ok(false);}
        return ResponseEntity.ok(boardService.write(boardDto, loginMid));
    }
}
