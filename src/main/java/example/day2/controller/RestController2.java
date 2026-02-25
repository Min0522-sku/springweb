package example.day2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RestController2 {
    //정수를 반환하는 메소드
    @GetMapping("/day02/task") // was(톰캣)주소/내가정의한주소, localhost:8080/day02/task, URL과 중복 불가
    @ResponseBody // Response(응답)Body(객체지향) : 응답 자료 자동 타입변환
    // java(객체지향) <--번역--> http(문자), java는 int를 반환하겠다고 하지만 http는 int 모름
    // 즉] java타입을 자동으로 http contents type 변환해준다. int -> application/json
    public int method1(){
        System.out.println("RestController2.method1");
        return 100;
    }

    //문자열 반환하는 메소드
    @GetMapping("/day02/task2")
    @ResponseBody // java String -> text/plain
    public String method2(){
        System.out.println("RestController2.method2");
        return "문자열";
    }

    //map 타입 반환하는 메소드
    @GetMapping("/day02/task3")
    @ResponseBody // java map -> application/json
    public Map<String, Object> method3(){
        Map<String, Object> map = new HashMap<>();
        map.put("유재석", 100);
        map.put("강호동", 90);
        return map;
    }

    // boolean 타입 반환하는 메소드
    @GetMapping("/day02/task4")
    @ResponseBody
    public boolean method4(){
        return true;
    }

    // DTO 타입 반환 하는 메소드
    @GetMapping("/day02/task5")@ResponseBody
    public TaskDto method5(){
        TaskDto taskDto = new TaskDto();
        taskDto.naem = "유재석";
        taskDto.point = 100;
        return  taskDto;
    }

    // 즉] String 제외한 자바의 대부분 타입은 application/json 으로 http content-type 으로 설정된다.
}

class TaskDto{String naem; int point;}
