package example.day07.practice7;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "enroll")
public class EnrollEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enrollId;
    private boolean status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "courseId")
    private CourseEntity courseEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId")
    private StudentEntity studentEntity;

    public EnrollDto toDto(){
        return EnrollDto.builder()
                .enrollId(enrollId)
                .status(status)
                .courseId(courseEntity.getCourseId())
                .studentId(studentEntity.getStudentId())
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }

}
