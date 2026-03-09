package example.day07.practice7;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public boolean 등록(CourseDto courseDto) {
        CourseEntity saved = courseRepository.save(courseDto.toEntity());
        if (saved.getCourseId() >= 1) return true;
        return false;
    }
}
