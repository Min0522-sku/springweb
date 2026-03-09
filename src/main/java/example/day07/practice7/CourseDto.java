package example.day07.practice7;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseDto {
    private Integer courseId;
    private String courseName;
    private String createDate;
    private String updateDate;


    public CourseEntity toEntity(){
        // 빌더 패턴
        // this 해당 메소드/함수 실행한 객체

        return CourseEntity.builder() // 빌더 시작
                .courseId(this.courseId)
                .courseName(this.courseName)
                .build();
    }
}
