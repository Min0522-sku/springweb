package example.day09.챕터6챕터7.repository;

import example.day09.챕터6챕터7.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<ArticleEntity, Long> {
}
