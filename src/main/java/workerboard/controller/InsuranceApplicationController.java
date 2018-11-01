package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.model.InsuranceApplication;
import workerboard.serivce.InsuranceApplicationService;

@RestController
@CrossOrigin
@RequestMapping("/api/application")
public class InsuranceApplicationController {

    private InsuranceApplicationService insuranceApplicationService;

    @Autowired
    public InsuranceApplicationController(InsuranceApplicationService insuranceApplicationService) {
        this.insuranceApplicationService = insuranceApplicationService;
    }

    @PostMapping
    ResponseEntity<InsuranceApplication> registerInsuranceApplication(@RequestBody InsuranceApplication insuranceApplication){
        return ResponseEntity.ok(insuranceApplicationService.registerInsuranceApplication(insuranceApplication));
    }
}
