package example.day03.롬복;

import lombok.*;

public class Exam3 {
    public static void main(String[] args) {
        /*
            롬복 lombok: 반복되는 코드들을 줄여주는 라이브러리
            1. 인텔리제이 설치
            설정 -> 플러그인 -> lombok 검색후 설치
            2. 프로젝트 의존성 추가
                https:www.start.spring.io
                디펜더시 lombok 검색후 추가 후 ex뭐시기 누른뒤 lombok 코드만 복사
                compileOnly 'org.projectlombok:lombok'
                annotationProcessor 'org.projectlombok:lombok'


        */

        StudentDto studentDto1 = new StudentDto();
        StudentDto studentDto2 = new StudentDto(1, "유재석");
        studentDto2.getSname();
        studentDto2.setSname("강호동");
        studentDto2.toString();

        // * 생성자는 매개변수의 순서대로 인자값을 전달해야한다. 유연성 떨어진다.
        //StudentDto studentDto3 = new StudentDto("유재석" , 1);
        // 해결책 : 빌더 패턴 (객체 만드는 패턴 = 유연성 제공)
        // 클래스명.builder().멤버변수명(값).멤버변수명(값).build();
        StudentDto studentDto4 = StudentDto.builder()
                .sno(1).sname("유재석").build();
        //장점 : 생성자를 쓰지 않으니 순서가 상관 없음
        //단점 : 맴버변수값에 대한 유효성 검사 필요


    }
}
@NoArgsConstructor // 컴파일 시 기본생성자 코드 자동 생성
@AllArgsConstructor // 컴파일 시 전체매개변수 생성자 코드 자동생성
//@RequiredArgsConstructor // final 매개변수 생성자 자동 생성
@Getter // getter 메소드 제공
@Setter // setter 메소드 제공
@ToString // toString 메소드 제공
    //@Data // = @Getter + Setter + ToString
@Builder // 빌더 패턴사용
class StudentDto{
    private int sno;
    private String sname;
}