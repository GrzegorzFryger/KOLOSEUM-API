package workerboard.repository;

import workerboard.model.ApplicationUser;
import workerboard.model.InsuranceApplication;

import java.time.LocalDate;
import java.util.List;

public interface InsuranceRepository extends BasicCustomRepository <InsuranceApplication>  {
    Integer countByRegisterDate(LocalDate registerDate);
    List<InsuranceApplication> findAllBySeller(ApplicationUser seller);
}
