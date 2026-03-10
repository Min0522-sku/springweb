package example.day07.practice7;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "course")
public class CourseEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String courseName;

    @OneToMany( mappedBy = "courseEntity")
    @ToString.Exclude @Builder.Default
    private List<EnrollEntity> enrollEntityList = new ArrayList<>();


    public CourseDto toDto(){
        return CourseDto.builder()
                .courseId(courseId)
                .courseName(courseName)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }
}
