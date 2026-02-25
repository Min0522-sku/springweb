package example.day2.pratice2;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {
    private List<Map<String, String>> list = new ArrayList<>();
    private Map<String, String> map1 = new HashMap<>();
    public PostController() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("pno", "1");
        map1.put("ptitle", "제목1");
        list.add(map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("pno", "2");
        map2.put("ptitle", "제목2");
        list.add(map2);
    }
    @PostMapping("/pratice2/post")
    public boolean 글쓰기(){
        return true;
    }
    @GetMapping("/pratice2/post")
    public List<Map<String, String>> 전체글조회(){
        return list;
    }
    @GetMapping("/pratice2/post/view")
    public Map<String, String> 개별글조회(){
        return map1;
    }
    @PutMapping("/pratice2/post")
    public boolean 개별글수정(){
        return true;
    }
    @DeleteMapping("/pratice2/post")
    public int 개별글삭제(){
        return 3;
    }
}
