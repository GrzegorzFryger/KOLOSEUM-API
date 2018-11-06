package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.ApplicationUserDto;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

     Optional<ApplicationUser> findByEmail(String userEmail);
}
