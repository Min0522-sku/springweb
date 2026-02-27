package example.종합.종합9.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 매개변수 없는 생성자 자동 생성
@AllArgsConstructor // 전체매개변수 생성자 자동생성
@Data // setter getter toString + RequiredArgsConstructor(final 멤버 변수 생성자)
public class BoardDto {
    private Integer bno; // null 값 대응하기 위해 Integer로 변경
    private String bcontent;
    private String bwriter;
    private String bdate;
}
