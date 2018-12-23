package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workerboard.model.Experience;
import workerboard.repository.CustomRepository.ScoreboardCustomRepository;

@Repository
public interface ExperienceRepository extends ScoreboardCustomRepository, JpaRepository<Experience,Long> {
}
