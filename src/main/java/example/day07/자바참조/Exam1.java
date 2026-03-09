package example.day07.자바참조;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class Exam1 {
    public static void main(String[] args) {
        // 자바는 객체지향 언어이다.
        // System: 클래스, System.out : 객체. println() : 함수/메소드
        // 즉] 특정한 객체가 특정한 함수 실행 .참조

        // 1 카테고리 객체 생성 == 클래스(설계도)로 메모리(인스턴스) 생성
        // 동일한 클래스(설계도)로 서로다른 객체(인스턴스) 생성
        Category category1 = new Category();
        category1.setCno(1); category1.setCname("공지사항");
        Category category2 = new Category();
        category2.setCno(2); category2.setCname("자유개시판");



        // 참조란 다른 값 접근한다 [FK]
        // ORM(JPA) 기술은 이러한 객체참조로 데이터베이스의 PK-FK 구현
        // 단방향 : FK 통해 PK 참조하는 것처럼 특정한 객체 통해 참조 객체를 참조한다
        // 개시물 객체 통해 카테고리 객체를 잠조

        Board board1 = new Board();
        board1.setBcontent("첫번째 개시물");
        // 첫번째 카테고리 객체를 개시물 객체에 대입
        board1.setCategory(category1); // 참조

        Board board2 = new Board();
        board2.setBcontent("두번째 개시물");
        board2.setCategory(category2);//참조


        // 양방향 : 두 객체간의 서로 참조하는 관계, 데이터베이스에는 존재하지 않음
        // ORM(JPA)는 단방향/양방향 모두 지원 특징: 조회가 빠름 JOIN 불필요
        // 순환참조문제점 복잡한 JOIN LAZY지연로딩 고려
        category1.getBoardList().add(board1); // 참조 대입
        category2.getBoardList().add(board2); // 참조 대입

        // 확인
        // FK 에서 PK 단방향 참조/조회
        System.out.println( board2.getCategory() );
        // PK 에서 FK 양방향 참조/조회
        // StackOverflowError : 양방향 참조 하다가 메모리 넘쳤다.
        // 해결방안 : 두 객체 간의 한쪽 필드에 @ToString.Exclude 주입한다.
        System.out.println( category1.getBoardList() );


    }

}
@Data
class Category{
    private int cno;
    private String cname;
    @ToString.Exclude
    private List<Board> boardList = new ArrayList<>();
}

@Data
class Board{
    private int bno;
    private String bcontent;
    private Category category;
}