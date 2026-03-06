package example.종합.종합10.board.service;


import example.종합.종합10.board.dto.CommentDto;
import example.종합.종합10.board.entity.BoardEntity;
import example.종합.종합10.board.entity.CommentEntity;
import example.종합.종합10.board.repository.BoardRepository;
import example.종합.종합10.board.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;

    public boolean 등록(CommentDto commentDto) {
        CommentEntity saveEntity = commentDto.toEntity();
        saveEntity = commentRepository.save(saveEntity);
        return saveEntity.getCno() >= 1;
    }

    public List<CommentDto> 전체조회(Integer bno) {
        List<CommentEntity> entityList = commentRepository.findByBno(bno);
        List<CommentDto> dtoList = new ArrayList<>();
        entityList.forEach(entity -> {
            dtoList.add(entity.toDto());
        });
        return dtoList;
    }


    public boolean 개별수정(CommentDto commentDto) {
        if (commentRepository.existsById(commentDto.getCno())) {
            commentRepository.save(commentDto.toEntity());
            return true;
        }
        return false;
    }

    public boolean 삭제(Integer cno) {
        if (commentRepository.existsById(cno)) {
            commentRepository.deleteById(cno);
            return true;
        }
        return false;
    }
}
