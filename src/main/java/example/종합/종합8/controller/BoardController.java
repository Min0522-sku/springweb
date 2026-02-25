package example.종합.종합8.controller;



import example.종합.종합8.model.dao.BoardDao;
import example.종합.종합8.model.dto.BoardDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController // 해당 컨드롤러는 http 기능 갖게 된다. <싱글톤 포함>
public class BoardController {
//    private BoardController(){}
//    private static final BoardController instance = new BoardController();
//    public static BoardController getInstance(){return instance;}

    private BoardDao bd = BoardDao.getInstance();
    @PostMapping
    public boolean writer(String bcontent, String bwriter){
        boolean result = bd.writer(bcontent,bwriter);
        return result;
    }
    @GetMapping
    public ArrayList<BoardDto> findAll(){
        ArrayList<BoardDto> result = bd.findAll();
        return result;
    }
    @PutMapping
    public boolean update(int bno, String bcontent){
        boolean result = bd.update(bno, bcontent);
        return result;
    }
    @DeleteMapping
    public boolean delete(int bno){
        boolean result = bd.delete(bno);
        return result;
    }
}
