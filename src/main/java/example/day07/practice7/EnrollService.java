package example.day07.practice7;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class EnrollService {
    @Autowired
    private EnrollRepository enrollRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    //{"status" : "true", "courseId" : 1, "studentId" : 1}
    //{"studentName" : "name"}
    //{"courseName" : "cname"}
    public boolean 등록(EnrollDto enrollDto) {
        CourseEntity courseEntity = courseRepository.findById(enrollDto.getCourseId()).get();
        StudentEntity studentEntity = studentRepository.findById(enrollDto.getStudentId()).get();
        EnrollEntity saveEntity = new EnrollEntity();
        saveEntity.setCourseEntity(courseEntity);
        saveEntity.setStudentEntity(studentEntity);
        enrollRepository.save(saveEntity);
        if (saveEntity.getEnrollId() >= 1) return true;
        return false;
    }

    public EnrollDto 개별조회(Integer enrollId) {

        Optional<EnrollEntity> optional = enrollRepository.findById(enrollId);

        if(optional.isPresent()){
            EnrollEntity entity = optional.get();
            EnrollDto enrollDto = entity.toDto();
            return enrollDto;
        }
        return null;
    }
}
