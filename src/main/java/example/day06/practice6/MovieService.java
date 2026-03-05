package example.day06.practice6;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public boolean 저장(MovieDto movieDto) {

        MovieEntity saved = movieRepository.save(movieDto.toEntity());
        if (saved.getMovieid() >= 1) return true;
        return false;
    }

    // 수정 ** @Transactional 필수 **
    // 수정시 여러개 setter 사용함으로 단일 실행

    public boolean 수정(MovieDto movieDto) {
        int updatePk = movieDto.getMovieid();
        Optional<MovieEntity> optional = movieRepository.findById(updatePk);
        if (optional.isPresent()) {
            MovieEntity updateEntity = optional.get();
            updateEntity.setTitle(movieDto.getTitle());
            updateEntity.setDirector(movieDto.getDirector());
            updateEntity.setReleasedate(movieDto.getReleasedate());
            updateEntity.setRating(movieDto.getRating());
            return true;
        } else return false;
    }

    public List<MovieDto> 전체출력() {
        List<MovieEntity> movieEntityList = movieRepository.findAll();

        List<MovieDto> movieDtoList = new ArrayList<>();
        movieEntityList.forEach(movieEntity -> {
            MovieDto movieDto = new MovieDto();
            movieDto.setMovieid(movieEntity.getMovieid());
            movieDto.setTitle(movieEntity.getTitle());
            movieDto.setDirector(movieEntity.getDirector());
            movieDto.setReleasedate(movieEntity.getReleasedate());
            movieDto.setRating(movieEntity.getRating());
            movieDto.setCreateDate(movieEntity.getCreateDate().toString());
            movieDto.setUpdateDate(movieEntity.getUpdateDate().toString());
            movieDtoList.add(movieDto);
        });
        return movieDtoList;
    }

    public MovieDto 개별출력(Integer movieid) {

        Optional<MovieEntity> movieOptional = movieRepository.findById(movieid);


        if (movieOptional.isPresent()) {

            MovieEntity movieEntity = movieOptional.get();

            MovieDto movieDto = new MovieDto();
            movieDto.setMovieid(movieEntity.getMovieid());
            movieDto.setTitle(movieEntity.getTitle());
            movieDto.setDirector(movieEntity.getDirector());
            movieDto.setReleasedate(movieEntity.getReleasedate());
            movieDto.setRating(movieEntity.getRating());
            movieDto.setCreateDate(movieEntity.getCreateDate().toString());
            movieDto.setUpdateDate(movieEntity.getUpdateDate().toString());

            return movieDto;
        } else {
            return null;
        }
    }
    public boolean 삭제(Integer movieid){
        movieRepository.deleteById(movieid);
        return true;
    }
}

