package example.day05.practice5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor@AllArgsConstructor
@Data@Builder
public class BookDto {
    private Integer bno;
    private String bname;
    private String bauthor;
    private String bpublisher;
}
// 데이터들을 dto 담아서 이동 객체