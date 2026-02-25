package example.day2.pratice2;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private ArrayList<BoardDto> boardDto = new ArrayList<>();
    @PostMapping("/board")
    public boolean boardWrite(String bcontent, String bwriter){
        boolean result = bcontent.isEmpty()&&bwriter.isEmpty();
        return result;
    }
    @GetMapping("/board")
    public ArrayList<BoardDto> boardPrint(){

        return boardDto;
    }
    @GetMapping("/board/detail")
    public BoardDto boardDetail(int bno){
        return boardDto.get(bno);
    }
    @DeleteMapping("/board")
    public boolean boardDelete(int bno){
        boardDto.remove(bno);
        return true;
    }
    @PutMapping("/board")
    public boolean boardUpdate(int bno, BoardDto bcontent){
        boardDto.set(bno, bcontent);
        return true;
    }

}

class BoardDto{
    private int bno;
    private String bcontent;
    private String bwriter;

}