package example.day2.pratice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class AppStart {
    public static void main(String[] args) {
        // SpringApplication.run(현재클래스);
        // 현재클래스는 @SpringBootApplication 의존성 주입받은 상태
        // 즉] 스프링 환경 세팅이 AppStart에 연결된 상태이므로 AppStart 클래스 실행
        // 클래스명.class : 클래스 정보(멤버/함수/상속/구현/생성자 등) 호풀
        SpringApplication.run(AppStart.class);
    }
}