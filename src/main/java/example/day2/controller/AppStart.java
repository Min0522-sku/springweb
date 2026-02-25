package example.day2.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan : 스프링이 실행될때 스프링 컨테이너 등록할 빈(@Conponent)들을 동일/하위패키지 찾아서 등록한다.
// @Component : @Controller @Service @RestController @Repository 등등 몇몇 어노테이션들은 내장됨.
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }

}
