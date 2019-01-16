package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workerboard.model.ToDoCard;
import workerboard.model.ToDoCardHistory;

import java.util.List;

@Repository
public interface ToDoCardHistoryRepository extends JpaRepository<ToDoCardHistory, Long> {

    List<ToDoCardHistory> findAllByCardId(Long id);
}
