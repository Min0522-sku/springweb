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
    @GetMapping("/query2") // http://localhost:8080/api/todo/query2?title=자바 공부&content=JPA 기본 개념 정리
    public ResponseEntity<?> query2(@RequestParam String title, @RequestParam String content){
        Map<String, Object> result = todoService.query2(title, content);
        return ResponseEntity.status(200).body(result);
    }

    // title 포함된 조회
    @GetMapping("/query3") // http://localhost:8080/api/todo/query3?title=실습
    public ResponseEntity<?> query3(@RequestParam String title){
        List<TodoDto> result = todoService.query3(title);
        return ResponseEntity.ok(result);
    }


    // 페이징 처리
    @GetMapping("/page") // http://localhost:8080/api/todo/page?page=1&size=3
    public ResponseEntity<?> page(@RequestParam int page, // 조회할 현재 페이지 번호
                                  @RequestParam int size){ // 페이지당 조회할 엔티티 개수

        // page.content : 조회된 엔티티들(list)
        // page.empty : 조회 실패 또는 없으면 true, 아니며 false
        // page.totalElements : 전체 자료 개수
        // page.totalPages : 전체 페이징 개수

        return ResponseEntity.ok(todoService.page(page,size));
    }

    // 페이징 처리2
    @GetMapping("/page2")
    public ResponseEntity<?> page2(@RequestParam String keyword, // 검색어
                                   @RequestParam(defaultValue = "1") int page, //defaultValue 값이 존재하지 않으면 초기값
                                   @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(todoService.page2(keyword, page, size));
    }
}
