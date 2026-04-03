package pratice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pratice.entity.TaskEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskDto {
    private Integer id;
    private String title;
    private String content;
    private String requester;
    private String status;
    private String createDate;
    private String updateDate;


    public TaskEntity toEntity(){
        // 빌더 패턴
        // this 해당 메소드/함수 실행한 객체

        return TaskEntity.builder() // 빌더 시작
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .requester(this.requester)
                .status(this.status)
                .build(); // 빌더 끝
    }
}
