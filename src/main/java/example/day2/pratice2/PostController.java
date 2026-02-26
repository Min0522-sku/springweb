package example.day2.pratice2;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pratice2/post")
public class PostController {

    @PostMapping
    public boolean 글쓰기(){
        return true;
    }
    @GetMapping
    public List<Map<String, Object>> 전체글조회(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("pno", "1");
        map1.put("ptitle", "제목1");
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("pno", "2");
        map2.put("ptitle", "제목2");
        list.add(map2);
        return list;
    }
    @GetMapping("/view")
    public Map<String, Object> 개별글조회(){
        Map<String, Object> map1 = new HashMap<>();
        map1.put("pno", "1");
        map1.put("ptitle", "제목1");
        return map1;
    }
    @PutMapping
    public boolean 개별글수정(){
        return true;
    }
    @DeleteMapping
    public int 개별글삭제(){
        return 3;
    }
}
