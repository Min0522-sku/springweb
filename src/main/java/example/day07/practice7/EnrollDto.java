package example.day07.practice7;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnrollDto {
    private Integer enrollId;
    private boolean status;
    private Integer courseId;
    private Integer studentId;
    private String createDate;
    private String updateDate;

    public EnrollEntity toEntity(){
        return EnrollEntity.builder()
                .enrollId(this.enrollId)
                .status(this.status)
                .courseEntity(CourseEntity.builder().courseId(this.courseId).build())
                .studentEntity(StudentEntity.builder().studentId(this.studentId).build())
                .build();
    }
}
