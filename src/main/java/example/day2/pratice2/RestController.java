package example.day2.pratice2;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/board")
public class RestController {
    @PostMapping
    public boolean boardWrite( @RequestBody BoardDto boardDto ){ // @RequestParam vs @RequestBody
        return true;
    }
    @GetMapping
    public ArrayList<BoardDto> boardPrint( ){
        ArrayList<BoardDto> list = new ArrayList<>();
        BoardDto boardDto1 = new BoardDto( 1 , "내용1" ,"작성자1");
        BoardDto boardDto2 = new BoardDto( 2 , "내용2" ,"작성자2");
        list.add( boardDto1 ); list.add( boardDto2 );
        return list;
    }
    @GetMapping("/detail")
    public BoardDto boardDetail( @RequestParam int bno ){
        BoardDto boardDto = new BoardDto( 1 , "내용1" ,"작성자1");
        return boardDto;
    }
    @DeleteMapping
    public boolean boardDelete(  @RequestParam  int bno ){
        return false;
    }
    @PutMapping
    public boolean boardUpdate( @RequestBody BoardDto boardDto ){
        return true;
    }

}

class BoardDto {
    private int bno;
    private String bcontent;
    private String bwriter;

    public BoardDto(){}
    public BoardDto(int bno, String bcontent, String bwriter) {
        this.bno = bno;
        this.bcontent = bcontent;
        this.bwriter = bwriter;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public String getBwriter() {
        return bwriter;
    }

    public void setBwriter(String bwriter) {
        this.bwriter = bwriter;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", bcontent='" + bcontent + '\'' +
                ", bwriter='" + bwriter + '\'' +
                '}';
    }
}