package example.종합.종합10.board.controller;

import example.종합.종합10.board.dto.BoardDto;
import example.종합.종합10.board.service.BoardService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired private BoardService boardService;

    @PostMapping
    public boolean 등록(@RequestBody BoardDto boardDto){
        boolean result = boardService.등록(boardDto);
        return result;
    }

    @GetMapping
    public List<BoardDto> 전체조회(){
        List<BoardDto> result = boardService.전체조회();
        return result;
    }

    @GetMapping("/detail")
    public BoardDto 개별조회(@RequestParam Integer bno){
        BoardDto result = boardService.개별조회(bno);
        return result;
    }

    @PutMapping
    public boolean 개별수정(@RequestBody BoardDto boardDto){
        return boardService.개별수정(boardDto);
    }

    @DeleteMapping
    public boolean 삭제(@RequestParam Integer bno){
        return boardService.삭제(bno);
    }
}
