package springweb.board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springweb.board.dto.BoardDto;
import springweb.board.service.BoardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

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
}
