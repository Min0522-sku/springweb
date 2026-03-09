package example.day07.practice7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public boolean 등록(StudentDto studentDto) {
        StudentEntity saved = studentRepository.save(studentDto.toEntity());
        if (saved.getStudentId() >= 1) return true;
        return false;
    }
}
