package example.day10;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Calculator{int plus(int x, int y);} // 추상메소드 구현{} 이없는 메소드
public class Exam2 {
    public static int plus(int x, int y){return x+y;}

    public static void main(String[] args) {
        int result = plus(3,2); // 5

        // 익명 구현체  구현체: 추상메소드를 구현한 객체 , 익명 구현채 : 클래스 없이 구현한 인스턴스
        // 인터페이스명 변수명 = new 인터페이스명() {오버라이딩};
        Calculator calc = new Calculator() {
            @Override
            public int plus(int x, int y) { // 재정의
                return x-y;
            }
        };

        int result1 = calc.plus(5,3); // 2

        // 람다 표현식, (매개변수) -> {구현부}
        Calculator calc2 = (x, y) -> x-y;
        int result2 = calc2.plus(5, 2); // 3

        // 람다 표현식을 사용하는 *함수명* 인터페이스 들
        // < > : 제너릭 : 인스턴스(객체) 생성할 때 인스턴스(객체)내 멤버들의 타입 정의
        // List<Integer> : List 객체 1개 생성시 내부에 Integer 타입으로 항목 구성
        // Map<String, Object> : Map 객체 1개 생성시 내부에 String 타입으로 key, Object 타입으로 value 구성
        // 객체내 멤버들의 타입을 매개로 정할 수 있는 타입

        // Function< T, R> T : 입력받은값 타입. R : 결과 반환값 타입 .apply(T) 메소드
        Function<Integer, Integer> function = x -> x *2; // return 생략시 {} 같이 생략
        System.out.println("function.apply(3) = " + function.apply(3)); // 6

        // Supplier< T > T: 결과 반환값 타입 , get() 메소드
        Supplier<Double> supplier = () -> {return 3.14;};
        System.out.println("supplier.get() = " + supplier.get());

        // Consumer<T> T : 입력받는값 타입 , 결과 값 업음, accept(T) 메소드
        Consumer<String> consumer = (str) -> System.out.println(str);
        consumer.accept("유재석");

        // Predicate<T>  T: 입력받아서 true/false 반환
        Predicate<Integer> predicate = (x) -> {return x%2 ==0;};
        System.out.println("predicate.test(3) = " + predicate.test(3));//false


    }
}
