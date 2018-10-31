package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workerboard.model.InsuranceApplication;

public interface InsuranceApplicationRepository extends JpaRepository<InsuranceApplication, Long> {
}
