package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workerboard.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {
}
