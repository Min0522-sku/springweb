package pratice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pratice.dto.TaskDto;
import pratice.entity.TaskEntity;
import pratice.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public boolean TaskPost(TaskDto taskDto){
        taskRepository.save(taskDto.toEntity());
        return true;
    }

    public List<TaskEntity> TaskAllFind(){
        return taskRepository.findAll();
    }

    public TaskEntity TaskFindId(Integer id){
        Optional<TaskEntity> entity = taskRepository.findById(id);
        if (entity.isPresent()){
            TaskEntity find = entity.get();
            return find;
        }else return null;
    }
    @Transactional
    public boolean TaskPut(Integer id, TaskDto taskDto){
        Optional<TaskEntity> entity = taskRepository.findById(id);
        if (entity.isPresent()){
            TaskEntity putEntity = entity.get();
            putEntity.setTitle(taskDto.getTitle());
            putEntity.setContent(taskDto.getContent());
            putEntity.setStatus(taskDto.getStatus());
            return true;
        }else return false;
    }

    public boolean TaskDelete(Integer id){
        taskRepository.deleteById(id);
        return true;
    }
}
