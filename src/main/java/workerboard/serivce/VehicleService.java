package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import workerboard.model.Vehicle;
import workerboard.repository.VehicleRepository;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    public List findVehicleProperty(Vehicle vehicle) {

        return vehicleRepository.findAll((Specification<Vehicle>) (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();


            if(vehicle.getId() != null){
                predicates.add(criteriaBuilder.equal(root.get("id"), vehicle.getId()));
            }

            if(vehicle.getModel() != null){
                predicates.add(criteriaBuilder.equal(root.get("model"), vehicle.getModel()));
            }

            if(vehicle.getMark() != null){
                predicates.add(criteriaBuilder.equal(root.get("mark"), vehicle.getMark()));
            }

            if(vehicle.getEngineCapacity() != null){
                predicates.add(criteriaBuilder.equal(root.get("engineCapacity"), vehicle.getEngineCapacity()));
            }


            if(vehicle.getLoadWeight() != null){
                predicates.add(criteriaBuilder.equal(root.get("loadWeight"), vehicle.getLoadWeight()));
            }

            if(vehicle.getEnginePower() != null){
                predicates.add(criteriaBuilder.equal(root.get("enginePower"), vehicle.getEnginePower()));
            }

            if(vehicle.getProductionYear() != null){
                predicates.add(criteriaBuilder.equal(root.get("productionYear"), vehicle.getProductionYear()));
            }

            if(vehicle.getSeatCount() != null){
                predicates.add(criteriaBuilder.equal(root.get("seatCount"), vehicle.getSeatCount()));
            }

            if(vehicle.getDoorsCount() != null){
                predicates.add(criteriaBuilder.equal(root.get("doorsCount"), vehicle.getDoorsCount()));
            }

            if(vehicle.getCylindersCount() != null){
                predicates.add(criteriaBuilder.equal(root.get("cylindersCount"), vehicle.getCylindersCount()));
            }

            if(vehicle.getGearboxCount() != null){
                predicates.add(criteriaBuilder.equal(root.get("gearboxCount"), vehicle.getGearboxCount()));
            }

            if(vehicle.getGearboxType() != null){
                predicates.add(criteriaBuilder.equal(root.get("gearboxType"), vehicle.getGearboxType()));
            }

            if(vehicle.getName() != null){
                predicates.add(criteriaBuilder.equal(root.get("name"), vehicle.getName()));
            }

            if(vehicle.getType() != null){
                predicates.add(criteriaBuilder.equal(root.get("type"), vehicle.getType()));
            }

            if(vehicle.getEngineHPower() != null){
                predicates.add(criteriaBuilder.equal(root.get("engineHPower"), vehicle.getEngineHPower()));
            }

            if(vehicle.getFuelType() != null){
                predicates.add(criteriaBuilder.equal(root.get("fuelType"), vehicle.getFuelType()));
            }

            if(vehicle.getBodyType() != null){
                predicates.add(criteriaBuilder.equal(root.get("bodyType"), vehicle.getBodyType()));
            }



            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }


    public List<String> findVehiclePropertyByAttribute(Vehicle vehicle, String attribute) {

        List<Vehicle> vehicles = findVehicleProperty(vehicle);
        Set<String> attributes = new TreeSet<>();

        for(Vehicle v : vehicles) {
            if(attribute.equals("model")){
                attributes.add(v.getModel());
            }
            if(attribute.equals("mark")){
                attributes.add(v.getMark());
            }
            if(attribute.equals("engineCapacity")){
                attributes.add(v.getEngineCapacity());
            }
            if(attribute.equals("loadWeight")){
                attributes.add(v.getLoadWeight());
            }
            if(attribute.equals("productionYear")){
                attributes.add(v.getProductionYear());
            }
            if(attribute.equals("cylindersCount")){
                attributes.add(v.getCylindersCount());
            }
            if(attribute.equals("seatCount")){
                attributes.add(v.getSeatCount());
            }
            if(attribute.equals("doorsCount")){
                attributes.add(v.getDoorsCount());
            }
            if(attribute.equals("gearboxCount")){
                attributes.add(v.getGearboxCount());
            }
            if(attribute.equals("gearboxType")){
                attributes.add(v.getGearboxType());
            }
            if(attribute.equals("name")){
                attributes.add(v.getName());
            }
            if(attribute.equals("type")){
                attributes.add(v.getType());
            }
            if(attribute.equals("engineHPower")){
                attributes.add(v.getEngineHPower());
            }
            if(attribute.equals("fuelType")){
                attributes.add(v.getFuelType());
            }
            if(attribute.equals("bodyType")){
                attributes.add(v.getBodyType());
            }
        }
        return new ArrayList<>(attributes);
    }
}
