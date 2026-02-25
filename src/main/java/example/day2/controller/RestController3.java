package example.day2.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

//@Component // 빈등록
//@Controller // + HTTP 통신기능   = 사용처 : View 반환
@RestController // + ResponseBody 포함  사용처 : 값(json) 반환
@RequestMapping("/day02") //  @RequestMapping("/공통URL") 해당 컨트롤러내 메소들이 사용하는 공통 URL 정의
public class RestController3 {
    // ResponseBody 생략 가능
    @GetMapping("/task6") // 공통 URL 을 가지므로 localhost:8080/day02/task6
    public String method1(){return "서버에게 받은 메세지";}

    // http://localhost:8080/day02/task7?name=유재석&age=40
    // 쿼리스트링 : URL 뒤로 ? 작성후 매개변수명=값&매개변수명2=값
    @GetMapping("/task7")
    public int method2(@RequestParam String name, @RequestParam int age){
        // @RequsetParam : URL 안에 포함된 퀴리스트링 매개변수 값 가져오는 어노테이션 *생략가능*
        System.out.println("RestController3.method2");
        System.out.println("name = " + name + ", age = " + age);
        return 3;
    }
    @GetMapping("/task8")
    public int method3(@RequestParam(required = false) String name, @RequestParam(name = "age") int 나이){
        // 만약 쿼리스트링의  매개변수명과 자바의 매개변수명이 다르면 @RequestParam( name = "쿼리스트링매개변수명")
        // 만약 쿼리스트링의 매개변수명을 필수로 하지 않을 경우 @RequestParam(required = false) , 기본값은 true
        System.out.println("RestController3.method3");
        System.out.println("name = " + name + ", 나이 = " + 나이);
        return 4;
    }


    @DeleteMapping("/task9")  // http://localhost:8080/day02/task7?name=유재석&age=40
    public int method4(String name, @RequestParam( defaultValue = "19") int age) {
        // 만약에 @RequestParam 생략해도 매개변수 매핑(연결) 가능하다
        // 만약에 쿼리스트링에 존재하지 않을때 기본값으로 설정 @RequestParam( defaultValue = 초기값)
        System.out.println("RestController3.method4");
        System.out.println("name = " + name + ", age = " + age);
        return 5;
    }
    // http://localhost:8080/day02/task10?name=유재석&age=40
    @DeleteMapping("/task10")
    // 여러개 매개변수를 하나의 Map 타입으로 받을수 있다.
    public int method5(@RequestParam Map<String, Objects> map){
        System.out.println("RestController3.method5");
        System.out.println("map = " + map);
        return 6;
    }

    @PostMapping("/task11")
    public int method5(@ModelAttribute ExamDto examDto){
        System.out.println("RestController3.method5");
        System.out.println("examDto = " + examDto);
        return 11;
    }
    // url 상의 매개변수때문에 쿼리스트링 상 매개변수 노출이됨
    // get delete -> 쿼리스트링 -> RequestParam/ModelAttribute 사용
    // post put -> + body 본문 + -> RequestBody
    // url 상의 매개변수 노출을 가리기 위한 Body(본문) 사용
    // 민감한 정보는 body 사용
    // 예시) 편지 봉투는 쿼리스트링 편지 내용은 body


    // Body : { "name" : "유재석", "age" : 40}
    // html -- >js --> java(controller -> dao
    @PostMapping("/task12")
    public int method12( @RequestBody ExamDto examDto){
        System.out.println("RestController3.method12");
        System.out.println("examDto = " + examDto);
        return 12;
    }


}
