package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.repository.InsuranceApplicationRepository;

@Service
public class InsuranceApplicationService {

    private InsuranceApplicationRepository insuranceApplicationRepository;

    @Autowired
    public InsuranceApplicationService(InsuranceApplicationRepository insuranceApplicationRepository) {
        this.insuranceApplicationRepository = insuranceApplicationRepository;
    }
}
