package example.종합.종합10.board.service;

import example.종합.종합10.board.dto.BoardDto;
import example.종합.종합10.board.entity.BoardEntity;
import example.종합.종합10.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired private BoardRepository boardRepository;

    public boolean 등록(BoardDto boardDto) {
        BoardEntity saveEntity = boardDto.toEntity();

        saveEntity = boardRepository.save(saveEntity);
        if (saveEntity.getBno() >= 1) return true;
        return false;
    }

    public List<BoardDto> 전체조회(){
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardDto> list = new ArrayList<>();
        entityList.forEach( entity -> {
            BoardDto boardDto = entity.toDto();
            list.add(boardDto);
        } );
        return list;
    }

    public BoardDto 개별조회(Integer bno) {

        Optional<BoardEntity> optional = boardRepository.findById(bno);

        if(optional.isPresent()){
            BoardEntity entity = optional.get();
            BoardDto boardDto = entity.toDto();
            return boardDto;
        }
        return null;
    }
    @Transactional
    public boolean 개별수정(BoardDto boardDto) {
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());

        if (optional.isPresent()){
            BoardEntity updateEntity = optional.get();
            updateEntity.setBtitle(boardDto.getBtitle());
            updateEntity.setBwriter(boardDto.getBwriter());
            updateEntity.setBcontent(boardDto.getBcontent());
            return true;
        }
        return false;
    }

    public boolean 삭제(Integer bno) {
        if(boardRepository.existsById(bno)){  // existsById(pk번호) : 존재하면 true 없으면 false
            boardRepository.deleteById(bno);
            return true;
        }
        return false;
    }
}
