package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import workerboard.model.Vehicle;
import workerboard.serivce.VehicleService;

import javax.xml.ws.Response;
import java.util.List;
import java.util.stream.Collectors;

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
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<List<Vehicle>> findVehicleProperty(@RequestBody Vehicle vehicle){

        return ResponseEntity.ok(vehicleService.findVehicleProperty(vehicle));
    }

    @PostMapping("/{attribute}")
    public ResponseEntity<List<String>> findVehicleProperty(@RequestBody Vehicle vehicle, @PathVariable("attribute") String attribute ){

        //temporary for angular
        if(attribute.equals("productionYear") ) {
            return ResponseEntity.ok(vehicleService.findAllByAttributeWithDistrict("productionYear"));
        }

        return ResponseEntity.ok(vehicleService.findVehiclePropertyByAttribute(vehicle, attribute));
    }



}
