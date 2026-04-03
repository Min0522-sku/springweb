package pratice.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pratice.dto.TaskDto;
import pratice.service.TaskService;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173") // 서로다른 port(프로그램식별번호) 번호 간의 통신 허용
// SOP 정책으로 서로 다른 도메인은 통신이 불가능하다. HTTP 보안 정책
// CORS : 교차 출처 리소스 공유, 즉] 서로 다른 도메인(8080스프링,5173리액트) 통신 공유 허용
@RequestMapping("/api/task") // 리액트 경로 vs 스프링 경로 중복 방지로 api 추가(권장)
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> TaskPost(@RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.TaskPost(taskDto));
    }
    @GetMapping
    public ResponseEntity<?> TaskAllFind(){
        return ResponseEntity.ok(taskService.TaskAllFind());
    }
    @GetMapping("/detail")
    public ResponseEntity<?> TaskFindId(@RequestParam Integer id){
        return ResponseEntity.ok(taskService.TaskFindId(id));
    }

    @PutMapping
    public ResponseEntity<?> TaskPut(@RequestParam Integer id, @RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.TaskPut(id,taskDto));
    }
    @DeleteMapping
    public ResponseEntity<?> TaskDelete(@RequestParam Integer id){
        return ResponseEntity.ok(taskService.TaskDelete(id));
    }
}
