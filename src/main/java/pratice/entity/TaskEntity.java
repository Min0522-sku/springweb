package pratice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pratice.dto.TaskDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table( name = "task")
public class TaskEntity extends BaseTime{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private String requester;

    private String status;

    public TaskDto toDto(){
        return TaskDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .requester(requester)
                .status(status)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())// BaseTime의 멤버변수가 private라 getter setter를 사용해야함
                .build();
    }
}
