package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workerboard.model.ApplicationUser;
import workerboard.model.ToDoCard;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoCard, Long> {
    List<ToDoCard> findAllByUser(ApplicationUser applicationUser);
}
