package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.NotFound;
import workerboard.model.InsuranceApplication;
import workerboard.serivce.InsuranceService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/application")
public class InsuranceController {

    private InsuranceService insuranceService;

    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping
    ResponseEntity<InsuranceApplication> registerInsuranceApplication(@RequestBody InsuranceApplication insuranceApplication){
        return ResponseEntity.ok(insuranceService.registerInsuranceApplication(insuranceApplication));
    }


    @GetMapping("/{id}")
    ResponseEntity<InsuranceApplication> getInsuranceApplicationById(@PathVariable("id") Long id ) throws NotFound {
        return ResponseEntity.ok(insuranceService.getInsuranceApplicationById(id));

    }

    @PutMapping("/{id}")
    ResponseEntity<InsuranceApplication> updateInsuranceApplication(@PathVariable("id") Long id, @RequestBody InsuranceApplication insuranceApplication) throws NotFound {
        return ResponseEntity.ok(insuranceService.updateInsuranceApplication(id, insuranceApplication));
    }

    @PostMapping("/{id}/accept")
    ResponseEntity<InsuranceApplication> acceptInsuranceApplication(@PathVariable("id") Long id, @RequestBody InsuranceApplication insuranceApplication) throws NotFound {
        return ResponseEntity.ok(insuranceService.acceptInsuranceApplication(id, insuranceApplication));
    }

    @GetMapping("/{id}/byUser")
    ResponseEntity<List<InsuranceApplication>> getInsuranceApplicationByUser(@PathVariable("id") Long id) throws NotFound {
        return ResponseEntity.ok(insuranceService.getInsuranceApplicationByUser(id));
    }

    @GetMapping("/search")
    ResponseEntity<List<InsuranceApplication>> findLike(@RequestParam Map<String,
            String> allRequestParams, ModelMap model) {

        return ResponseEntity.ok(insuranceService.findLike(allRequestParams));
    }


}
