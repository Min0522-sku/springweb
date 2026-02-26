package example.day03.pratice4;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/day03/pratice4/attendance")

public class AttendanceController {
    @PostMapping //http://localhost:8080/day03/pratice4/attendance
    public boolean 출석등록(@RequestBody AttendanceDto attendanceDto){
        System.out.println("attendanceDto = " + attendanceDto);
        System.out.println("AttendanceController.출석등록");
        return true;
    }
    @GetMapping //http://localhost:8080/day03/pratice4/attendance
    public List<AttendanceDto> 출석전체조회(){
        List<AttendanceDto> list = new ArrayList<>();
        list.add(new AttendanceDto(1,"유재석","2026-02-26","출석"));
        list.add(AttendanceDto.builder().status("출석").date("2026-02-26").studentName("강호동").ano(2).build());
        return list;
    }
    @GetMapping("/detail") //http://localhost:8080/day03/pratice4/attendance/detail
    public List<AttendanceDto> 출석개별조회(){
        List<AttendanceDto> list = new ArrayList<>();
        list.add(new AttendanceDto(1,"유재석","2026-02-26","출석"));
        list.add(AttendanceDto.builder().status("출석").date("2026-02-26").studentName("강호동").ano(2).build());
        return list;
    }
    @DeleteMapping
    public boolean 출석삭제(@RequestParam int ano){
        System.out.println("AttendanceController.method2");
        System.out.println("ano = " + ano);
        return false;
    }
    @PutMapping
    public boolean 출석수정(@RequestBody AttendanceDto updateDto){
        System.out.println("AttendanceController.method4");
        System.out.println("updateDto = " + updateDto);
        return false;
    }
}
