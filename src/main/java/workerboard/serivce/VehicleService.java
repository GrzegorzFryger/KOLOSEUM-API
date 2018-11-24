package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import workerboard.model.Vehicle;
import workerboard.repository.VehicleRepository;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
public class VehicleService extends BasicAbstractService<Vehicle>{

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        super.setBasicRepository(vehicleRepository);
        this.vehicleRepository = vehicleRepository;
    }


    public List<Vehicle> findVehicleProperty(Vehicle vehicle) {

    return super.findAllByCriteria(andCriteria(),
            vehicle);
    }


    public List<String> findVehiclePropertyByAttribute(Vehicle vehicle, String attribute) {

        return super.findPropertyByAttribute(andCriteria(),
                vehicle,
                attribute,
                Vehicle.class);
    }
}
