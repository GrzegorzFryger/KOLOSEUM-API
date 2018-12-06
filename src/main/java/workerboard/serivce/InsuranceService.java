package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.exception.NotFound;
import workerboard.model.InsuranceApplication;
import workerboard.model.ServiceMessage;
import workerboard.model.enums.InsuranceApplicationState;
import workerboard.model.enums.ServiceMessageType;
import workerboard.repository.InsuranceHistoryRepository;
import workerboard.repository.InsuranceRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InsuranceService {

    private InsuranceRepository insuranceRepository;
    private InsuranceHistoryRepository insuranceHistoryRepository;
    private RisksService risksService;

    @Autowired
    public InsuranceService(InsuranceRepository insuranceRepository, RisksService risksService, InsuranceHistoryRepository insuranceHistoryRepository) {
        this.insuranceRepository = insuranceRepository;
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
        insuranceApplication.setState(InsuranceApplicationState.APPLICATION);

        insuranceHistoryRepository.save(insuranceApplication.getPersons().get(0).getInsuranceHistory());
        insuranceRepository.save(insuranceApplication);
        return insuranceApplication;

    }

    private String getCalculationNumber() {
        return "" + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth() + "00" + (insuranceRepository.countByRegisterDate(LocalDate.now()) + 1);
    }

    private boolean isApplicationValid(InsuranceApplication insuranceApplication) {
        if(insuranceApplication.getVehicle() == null) return false;
        if(Integer.valueOf(insuranceApplication.getVehicle().getVehicleValue()) == 0 || insuranceApplication.getVehicle().getVehicleValue() == null) return false;
        if(insuranceApplication.getPersons().isEmpty() || insuranceApplication.getPersons() == null) return false;
        if(insuranceApplication.getPersons().get(0).getDayOfBirth() == null) return false;

        return insuranceApplication.getPersons().get(0).getDrivingLicenseIssueDate() != null;

    }

    public InsuranceApplication getInsuranceApplicationById(Long id) throws NotFound {

        Optional<InsuranceApplication> applicationOptional = insuranceRepository.findById(id);
        if(applicationOptional.isPresent()){
            InsuranceApplication insuranceApplication = applicationOptional.get();
            insuranceApplication.setRiskVariants(risksService.getAvailableRisks(insuranceApplication));
            return insuranceApplication;
        }

        throw new NotFound("Application with ID " + id + " not found");
    }

    public InsuranceApplication updateInsuranceApplication(Long id, InsuranceApplication insuranceApplication) throws NotFound {
        Optional<InsuranceApplication> optionalApplication = insuranceRepository.findById(id);
        if(optionalApplication.isPresent()) {
            InsuranceApplication applicationFromDB = optionalApplication.get();
            applicationFromDB = insuranceApplication;
            return insuranceRepository.save(applicationFromDB);
        }

        throw new NotFound("Application with ID: " + id + "not found");
    }

    public InsuranceApplication acceptInsuranceApplication(Long id, InsuranceApplication insuranceApplication) throws NotFound {
        Optional<InsuranceApplication> optionalApplication = insuranceRepository.findById(id);
        if(optionalApplication.isPresent()){
            InsuranceApplication applicationFromDb = optionalApplication.get();
            applicationFromDb = insuranceApplication;
            applicationFromDb.setState(InsuranceApplicationState.POLICY);
            return insuranceRepository.save(applicationFromDb);

        }
        throw new NotFound("Application with ID: " + id + "not found");
    }
}
