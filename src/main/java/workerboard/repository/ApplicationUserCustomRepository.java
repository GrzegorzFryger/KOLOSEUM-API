package workerboard.repository;

import org.springframework.stereotype.Repository;
import workerboard.model.ApplicationUser;

import java.util.Optional;

@Repository
public interface ApplicationUserCustomRepository extends BasicCustomRepository<ApplicationUser> {

     Optional<ApplicationUser> findByEmail(String userEmail);
}
