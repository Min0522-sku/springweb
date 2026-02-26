package example.day03.pratice4;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AttendanceDto{
    private Integer ano;
    private String studentName;
    private String date;
    private String status;
}
