package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workerboard.model.InsuranceHistory;

@Repository
public interface InsuranceHistoryRepository extends JpaRepository<InsuranceHistory, Long> {
}
