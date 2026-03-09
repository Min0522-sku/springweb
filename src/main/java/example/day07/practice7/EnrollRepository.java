package example.day07.practice7;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Integer> {
}
