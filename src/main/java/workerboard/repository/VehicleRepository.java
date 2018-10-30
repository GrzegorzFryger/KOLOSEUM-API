package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import workerboard.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor {


}
