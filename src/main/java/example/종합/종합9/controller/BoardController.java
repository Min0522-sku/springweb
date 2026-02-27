package example.종합.종합9.controller;

import example.종합.종합9.model.dao.BoardDao;
import example.종합.종합9.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 빈(객체)등록 + http 기능 + http 응답객체
@RequestMapping("/board")
public class BoardController {
    @Autowired private BoardDao boardDao; // 의존성 주입 DI : 등록된 빈 가져오기

    @GetMapping
    public List<BoardDto> findAll(){
        List<BoardDto> result = boardDao.findAll();
        return result;
    }

    @PostMapping
    public boolean wtite(@RequestBody BoardDto boardDto){
        boolean result = boardDao.write(boardDto);
        return result;
    }
    @PutMapping
    public boolean update(@RequestBody BoardDto boardDto){
        boolean result = boardDao.update(boardDto);
        return result;
    }
    @DeleteMapping
    public boolean delete(@RequestParam int bno){
        boolean result = boardDao.delete(bno);
        return result;
    }

}
