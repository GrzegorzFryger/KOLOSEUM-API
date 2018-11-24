package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import workerboard.model.ApplicationUser;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends BasicRepository<ApplicationUser> {

     Optional<ApplicationUser> findByEmail(String userEmail);
}
