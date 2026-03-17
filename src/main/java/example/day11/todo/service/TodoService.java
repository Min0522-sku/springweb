package example.day11.todo.service;

import example.day11.todo.dto.TodoDto;
import example.day11.todo.entity.TodoEntity;
import example.day11.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional // 트랜젝션
public class TodoService {
    private final TodoRepository todoRepository;

    public List<TodoDto> findAll() {
        // 1] 모든 엔티티 조회
        List<TodoEntity> entityList = todoRepository.findAll();
        // 2] 모든 엔티티 --> 모든 Dto 변환
//        // 방법 1
//        List<TodoDto> list = new ArrayList<>();
//        for (int i = 0; i < entityList.size(); i++) {
//            TodoEntity entity = entityList.get(i);
//            list.add(entity.toDto());
//        }
//        // 방법 2
//        List<TodoDto> list1 = new ArrayList<>();
//        entityList.stream().forEach(entity -> {
//            list1.add(entity.toDto());
//        }); // forEach 함수는 반환이 없다
//
//        // 방법 3
//        List<TodoDto> list2 = entityList.stream() // 스트림(데이터들 흐름) 시작
//                .map(entity -> entity.toDto()) // 중간연산
//                .collect(Collectors.toList()); // 최종출력
//
//        // 방법 4
        List<TodoDto> list3 = entityList.stream()
                .map(TodoEntity :: toDto) // 중간연산, 람다식 대신에 메소드래퍼런스API(미리정의된 메소드)
                // 클래스명 :: 메소드명
                .collect(Collectors.toList());

        return list3;
    }

    public TodoDto findById(int id){
//        // 방법 1
//        Optional<TodoEntity> optional = todoRepository.findById(id);
//        if (optional.isPresent()){
//            TodoDto todoDto = optional.get().toDto();
//        }
        // 방법 2
        TodoDto todoDto = todoRepository.findById(id)
                // 스트림을 사용하지 않고 Optional 에서 map 메소드 지원
                .map(TodoEntity :: toDto)
                .orElse(null); // 만약에 조회 실패하면 null 반환 or 예외처리 // 스트림 사용한다면 .findFirst().ofElse(null)

        return todoDto;
    }

    // title 개별조회
    public TodoDto query1(String title){
        // 쿼리메소드 호출
       TodoEntity entity = todoRepository.findByTitle(title);
        // 네이티브 쿼리 호출
       TodoEntity entity1 = todoRepository.query2(title);
       return entity1.toDto();
    }

    // title 과 content 개별조회
    public Map<String, Object> query2(String title, String content){
        // 쿼리 메소드
         Map<String, Object> result = todoRepository.findByTitleAndContent(title,content);
        System.out.println("result= " + result.toString());
        // 네이티브 쿼리
        return todoRepository.query2(title,content);
    }

    // title 포함된 조회
    public List<TodoDto> query3(String title){
        // 쿼리 메소드
        List<TodoEntity> entityList = todoRepository.findByTitleContaining(title);
        // 네이티브 쿼리
        List<TodoEntity> entityList1 = todoRepository.query3(title);


        return entityList1.stream()
                .map(TodoEntity :: toDto) // 엔티티 -> dto 변환
                .collect(Collectors.toList()); // 최종출력은 List 타입
    }

    // 페이징 처리
    // Page 인터페이스 : 페이징 처리 정보 담는 인터페이스
    public Page<TodoDto> page(int page, int size){
        // 페이징 옵션 설정 PageRequest 구현체 .of(조회할 페이지 번호, 페이지당 개수, 정렬)
            // page-1 : JPA는 페이징 번호가 0부터 시작함으로써 1페이지는 0
            // Sort.by(Sort.Direction.DESC, "정렬기준필드명" ) : id 속성명으로 내림차순 오름차순은 생략
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC, "id"));
        // findxxx(pageRequset) 구현체를 포함한다, 반환값은 Page<엔티티>
        Page<TodoEntity> entityPage = todoRepository.findAll(pageRequest); // 전체조회에 대한 페이징 처리
        // Page<엔티티> --> Page<Dto> 변환하기

        return entityPage.map( TodoEntity :: toDto); // map 과 레퍼런스 api 이용한 변환
        // return entityPage.map(entity -> entity.toDto());
    }

    // 페이징 처리 2
    public Page<TodoDto> page2(String keyword, int page, int size){
        // 페이징 옵션, 구현체 만들기
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC, "id"));
        // 전체 조회 인지?? 키워드 조회 인지??
        Page<TodoEntity> result; //  조회 결과 담는 Page 타입
        if (keyword == null || keyword.isBlank()){ //만약 키워드가 비어있으면 전체조회
            result = todoRepository.findAll(pageRequest); // 전체조회 + 페이징처리
        }else { // 아니면 키워드 조회
            result = todoRepository.query4(keyword, pageRequest); // 개별조회 메소드 + 페이징처리
        }
        return result.map(TodoEntity :: toDto);
    }
}
