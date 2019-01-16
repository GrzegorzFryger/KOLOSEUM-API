package workerboard.serivce;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.model.Vehicle;
import workerboard.repository.CustomRepository.SortType;
import workerboard.repository.VehicleCustomRepository;

import java.util.List;



@Service
public class VehicleService extends BasicAbstractService<Vehicle>{

    private VehicleCustomRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleCustomRepository vehicleRepository) {
        super.setBasicRepository(vehicleRepository);
        this.vehicleRepository = vehicleRepository;
    }


    public List<Vehicle> findVehicleProperty(Vehicle vehicle) {

    return super.findAllByCriteria(andCriteria(),
            vehicle);
    }

    public List<String> findVehiclePropertyByAttribute(Vehicle vehicle, String attribute) {

        return findPropertyByAttribute(andCriteria(),
                vehicle,
                attribute,
                Vehicle.class,true, SortType.DESC);
    }

    public List<String> findAllByAttributeWithDistrict( String attribute) {


        return vehicleRepository.findAllProjection(Vehicle.class,attribute,true , SortType.DESC);

    }


}
