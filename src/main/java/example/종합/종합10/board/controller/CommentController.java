package example.종합.종합10.board.controller;


import example.종합.종합10.board.dto.CommentDto;
import example.종합.종합10.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public boolean 등록(@RequestBody CommentDto commentDto){
        boolean result = commentService.등록(commentDto);
        return result;
    }

    @GetMapping
    public List<CommentDto> 전체조회(@RequestParam Integer bno){
        List<CommentDto> result = commentService.전체조회(bno);
        return result;
    }

    @PutMapping
    public boolean 개별수정(@RequestBody CommentDto commentDto){
        return commentService.개별수정(commentDto);
    }

    @DeleteMapping
    public boolean 삭제(@RequestParam Integer cno){
        return commentService.삭제(cno);
    }
}
