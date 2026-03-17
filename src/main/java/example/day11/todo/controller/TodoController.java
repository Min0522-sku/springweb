package example.day11.todo.controller;

import example.day11.todo.dto.TodoDto;
import example.day11.todo.service.TodoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor // final 멤버변수 생성자 제공
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("") // http://localhost:8080/api/todo
    //ResponseEntity : 응답 객체, 사용 목적 : 반환값 외 추가적인 자료 포함 <응답 코드>
    // < > : 제네릭, < ? > 제네릭에 ? 사용시 Object 와 동일하게 모든 타입을 대입 가능
    public ResponseEntity<?> findAll(){
        List<TodoDto> result = todoService.findAll();
        return ResponseEntity.status(200).body(result); // HTTP 응답코드 200 성공의미 or .ok
    }

    @GetMapping("/detail") // http://localhost:8080/api/todo/detail?id=1
    public ResponseEntity<?> findById(@RequestParam int id){
        TodoDto result = todoService.findById(id);
        return ResponseEntity.status(200).body(result);
    }

    // title 개별 조회
    @GetMapping("/query1") // http://localhost:8080/api/todo/query1?title=자바 공부
    public ResponseEntity<?> query1(@RequestParam String title){
        TodoDto result = todoService.query1(title);
        return  ResponseEntity.ok(result);
    }

    // title 과 content 개별조회
    @GetMapping("/query2")
    public ResponseEntity<?> query2(@RequestParam String title, @RequestParam String content){
        Map<String, Object> result = todoService.query2(title, content);
        return ResponseEntity.status(200).body(result);
    }

}
