package example.day07.practice7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public boolean 등록(@RequestBody StudentDto studentDto ){
        boolean result = studentService.등록(studentDto);
        return result;
    }
}
