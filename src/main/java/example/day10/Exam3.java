package example.day10;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exam3 {
    public static void main(String[] args) {
        // 람다 표현식 (매개변수) -> {구현부}
        // 스트림API : 데이터(매개변수) --> 중간연산 --> 최종출력

        List<Integer> number = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 리스트변수명.stream().forEach(); 중간연산 없이 바로 최종출력
        number.stream().forEach((x) -> {
            System.out.println(x * 2 + "\t");
        });

        // 리스트 변수명.stream().map(중간연산).collect(최종출력);
        // 매개변수에 반복변수를 하나씩 대입하여 return 있는 반복문, 반복 return 값을 .collect( Collectors.toxxx()) 반환 받는다
        List<Integer> result =
                number.stream().map((x) -> {
                    return x * 2;
                }).collect(Collectors.toList());
        System.out.println("\nresult = " + result);

        // 리스트변수명.stream().map(중간연산).forEach(최종출력);
        number.stream().map(x -> x * 2).forEach(result1 -> {
            System.out.println("result1 = " + result1);
        });

        // 리스트 변수명.stream().filter(중간연산).forEach(최종출력);
        number.stream() // 리스트내 흐름시작
                .filter(x -> x % 2 == 0) // 중간연산, 짝수 찾기 반환
                .forEach(y -> System.out.println(y));

        // 정렬 중간연산
        number.stream()
                .sorted(Comparator.reverseOrder()) // 중간연산, 오름차순, 내림차순 Comparator.reverseOrder()
                .forEach(y -> System.out.println("y = " + y));
        // 중복제거 중간연산
        number.stream().distinct().collect(Collectors.toList());

        //
        number.stream()
                .limit(5) // 중간연산 , 앞에서 부터 n개 까지
                .forEach(y-> System.out.println("y = " + y));



        /*
            스트림 : 데이터 다니는 연속적인 흐름
                - 데이터들 --> 중간연산 --> 최종연산
                - 중간연산은 여러개 가능
                - 최종연산은 반드시 1개 가능
            주요 연산
                중간연산 : .map() .filter() .sorted() .distinct() .limit()
                최종연산 : forEach() collect()

        */

        // vs
        //for( int index = 0 ; index <= number.size() -1 ; index++ ){
        //if( number.get(index) % 2 == 0 ){
        //System.out.println("number.get(index)  = " + number.get(index) );
        //}
        //}


        /*
        List<BoardDto> list = new ArrayList<>(); // 여러개 dto 담는 리스트
        for( int i = 0 ; i<=entityList.size()-1; i++ ){
            BoardDto boardDto = entityList.get(i).toDto();
            list.add( boardDto );
        }
        // vs
        List<BoardDto> list = new ArrayList<>(); // 여러개 dto 담는 리스트
        entityList.forEach( entity -> {  // 리스트변수명.forEach( 반복변수 -> { 실행문; } );
            BoardDto boardDto = entity.toDto(); // 3] 엔티티 하나씩 dto로 변환
            list.add( boardDto ); // 4] 새로운 리스트에 담기
        } );
        // vs
        List<BoardDto> list =
                entityList.stream().map( entity -> entity.toDto() ).collect( Collectors.toList() );
       */
    }
}
