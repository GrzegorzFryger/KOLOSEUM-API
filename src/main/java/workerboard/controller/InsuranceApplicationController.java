package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
