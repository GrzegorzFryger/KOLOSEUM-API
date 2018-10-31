package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.model.Vehicle;
import workerboard.serivce.VehicleService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @PostMapping
    public ResponseEntity<List<Vehicle>> findVehicleProperty(@RequestBody Vehicle vehicle){
        return ResponseEntity.ok(vehicleService.findVehicleProperty(vehicle));
    }

}
