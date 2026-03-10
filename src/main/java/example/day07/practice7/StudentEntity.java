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
@Table(name = "student")
public class StudentEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String studentName;

    @OneToMany( mappedBy = "studentEntity")
    @ToString.Exclude @Builder.Default
    private List<EnrollEntity> enrollEntityList = new ArrayList<>();
    public StudentDto toDto(){
        return StudentDto.builder()
                .studentId(studentId)
                .studentName(studentName)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }
}
