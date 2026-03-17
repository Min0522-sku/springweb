package example.day11.todo.repository;

import example.day11.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer>{
    // JpaRepository 로부터 상속받으면 .save() .find() .delete() 등등 제공 받음
    // ** findAll() 전체조회, findById(pk값) 식별자 1개조회, 등등 그외 없으면 만들기

    // 그외 만들기
    // 쿼리 메소드 : sql 직접 작성하지 않고 추상메소드 이름으로 쿼리 자동 생성 < 카멜 표기법>
        // title 으로 조회, 반환타입 findBy필드명(타입 매개변수);
    TodoEntity findByTitle(String title); // { 구현부 } 없는 추상메소드

    // 네이티브 쿼리
        // 연동된 데이터베이스 쿼리 사용 가능하다
    //@Query(value = "네이티브 SQL문", nativeQuery = true) select * from 찾고자하는 테이블 where 찾을 기준 필드명 = :매개변수명
    @Query(value = "select * from todo where title = :title", nativeQuery = true)
    TodoEntity query2(String title);

    // JPQL
}
