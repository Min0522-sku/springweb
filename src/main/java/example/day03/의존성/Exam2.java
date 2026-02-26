package example.day03.의존성;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Exam2 {
}
// 방법 1 : 다른 클래스의 메소드를 호출하는
class TaskDao1{void method1(){}}
class TaskController1{
    void method(){
        TaskDao1 taskDao1 = new TaskDao1();
        taskDao1.method1();
    }
}
// 방법2 : 다른 클래스의 메소드를 호출 2(static)
class TaskDao2{ static void method1(){}} // static 메소드는 객체 없이 호출 가능
class TaskController2{
    void method(){
        TaskDao2.method1();
    }
}

//방법3 : 다른 클래스의 메소드를 호출3(싱글톤)
class TaskDao3{
    private TaskDao3(){}
    private static TaskDao3 instance = new TaskDao3();
    public static TaskDao3 getInstance(){return instance;}
    void method1(){}
}
class TaskController3{
    void method(){
        TaskDao3.getInstance().method1();
    }
}
// 방법4 : 다른 클래스의 메소드를 호출4(스프링방식)
@Component // IOC 스프링 컨테이너 빈 등록
class TaskDao4{void method1(){}}
@Component
class TaskController4{
    //DI 방법1 해당하는 변수에 스프링 빈에 등록된 객체를 주입해준다
    //@Autowired private TaskDao4 taskDao4;
    //DI 방법2 권장방법
    private final TaskDao4 taskDao4;
    @Autowired
    public TaskController4(TaskDao4 taskDao4){
        this.taskDao4 = taskDao4;
    }
    void  method(){
        taskDao4.method1();
    }
}


/*
    IOC : 제어의 역전, 개발자가 직접해야하는 객체 제어권을 스프링에게 위임
        정의: 객체의 생성과 관리의 *제어권*을 개발자가 아닌 스프링에게 위임한다.
        스프링에서 스프링 *컨테이너* 가 담당한다.
        목적: 개발자에게 편의성, 규칙성(협업간의 객체충돌)에 따른 관리
        주요 어노테이션
            @Component : 스프링 컨테이너에 클래스(Bean 빈) 의 정보를 등록한다
            Spring MVC 내장 : @Controller, @RestController @Service @Respository @Configuration 등등
            *즉] MVC 패턴은 IOC 기반이면서 필수 이다.
        *빈 = 객체 , 컴포넌트 =재사용가능한 모듈, =모듈 최소한 기능단위

    DI : 의존성 주입, IOC 표현하기위한 기술 중 하나, 위임한 객체 제어권에서 객체 가져오기
        정의 : 객체를 직접 생성하지 않고 외부(스프링 컨테이너)로 부터 객체 주입받도록 하는 방법
        목적: 객체들간의 결합도(관계)를 낮추고 유지보수성 향상
        주요어노테이션
            @AutoWrired : 스프링 컨테이너에 등록된 빈(객체)를 꺼내온다.
*/