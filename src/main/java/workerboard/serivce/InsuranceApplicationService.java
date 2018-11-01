package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.model.InsuranceApplication;
import workerboard.model.ServiceMessage;
import workerboard.model.enums.ServiceMessageType;
import workerboard.repository.InsuranceApplicationRepository;
import workerboard.repository.InsuranceHistoryRepository;

import java.time.LocalDate;

@Service
public class InsuranceApplicationService {

    private InsuranceApplicationRepository insuranceApplicationRepository;
    private InsuranceHistoryRepository insuranceHistoryRepository;
    private RisksService risksService;

    @Autowired
    public InsuranceApplicationService(InsuranceApplicationRepository insuranceApplicationRepository, RisksService risksService, InsuranceHistoryRepository insuranceHistoryRepository) {
        this.insuranceApplicationRepository = insuranceApplicationRepository;
        this.risksService = risksService;
        this.insuranceHistoryRepository = insuranceHistoryRepository;
    }

    public InsuranceApplication registerInsuranceApplication(InsuranceApplication insuranceApplication) {
        if(!isApplicationValid(insuranceApplication)){
            insuranceApplication.addMessage(new ServiceMessage(ServiceMessageType.ERROR, "Tariffication attribute is missing"));
            return insuranceApplication;
        }

        insuranceApplication.setRiskVariants(risksService.getAvailableRisks(insuranceApplication));
        insuranceApplication.setNumber(getCalculationNumber());

        insuranceHistoryRepository.save(insuranceApplication.getPersons().get(0).getInsuranceHistory());
        insuranceApplicationRepository.save(insuranceApplication);
        return insuranceApplication;

    }

    private String getCalculationNumber() {
        return "" + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth() + "00" + (insuranceApplicationRepository.countByRegisterDate(LocalDate.now()) + 1) ;
    }

    private boolean isApplicationValid(InsuranceApplication insuranceApplication) {
        if(insuranceApplication.getVehicle() == null) return false;
        if(Integer.valueOf(insuranceApplication.getVehicle().getVehicleValue()) == 0 || insuranceApplication.getVehicle().getVehicleValue() == null) return false;
        if(insuranceApplication.getPersons().isEmpty() || insuranceApplication.getPersons() == null) return false;
        if(insuranceApplication.getPersons().get(0).getDayOfBirth() == null) return false;

        return insuranceApplication.getPersons().get(0).getDrivingLicenseIssueDate() != null;

    }
}
