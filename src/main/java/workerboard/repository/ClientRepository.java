package workerboard.repository;

import org.springframework.stereotype.Repository;
import workerboard.model.Person;

@Repository
public interface ClientRepository extends BasicCustomRepository<Person> {
}
