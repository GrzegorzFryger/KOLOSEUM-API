package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workerboard.model.ToDoCard;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoCard, Long> {
}
