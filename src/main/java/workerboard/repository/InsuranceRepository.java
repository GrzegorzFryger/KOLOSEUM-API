package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workerboard.model.ApplicationUser;
import workerboard.model.InsuranceApplication;

import java.time.LocalDate;
import java.util.List;

public interface InsuranceRepository extends JpaRepository<InsuranceApplication, Long> {
    Integer countByRegisterDate(LocalDate registerDate);
    List<InsuranceApplication> findAllBySeller(ApplicationUser seller);
}
