package workerboard.serivce;

import org.springframework.stereotype.Service;
import workerboard.model.InsuranceApplication;
import workerboard.model.InsuranceHistory;
import workerboard.model.dto.PremiumDto;
import workerboard.model.dto.RiskDtoDto;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class RisksService {


    List<RiskDtoDto> getAvailableRisks(InsuranceApplication insuranceApplication) {
        List<RiskDtoDto> risks = new ArrayList<>();
        risks.add(new RiskDtoDto("NNW Basic", 30000, getPremiumByPrice(150)));
        risks.add(new RiskDtoDto("NNW Premium", 50000, getPremiumByPrice(240)));
        risks.add(new RiskDtoDto("Assistance Basic", 15000, getPremiumByPrice(160)));
        risks.add(new RiskDtoDto("Assistance Premium", 15000, getPremiumByPrice(220)));
        risks.add(new RiskDtoDto("Szyby", 5000, getPremiumByPrice(120)));
        risks.add(new RiskDtoDto("AC", Double.valueOf(insuranceApplication.getVehicle().getVehicleValue()), getPremiumByPrice(getACPrice(insuranceApplication))));
        risks.add(new RiskDtoDto("OC", Double.valueOf(insuranceApplication.getVehicle().getVehicleValue()), getPremiumByPrice(getOCPrice(insuranceApplication))));


        return risks;

    }

    private int getOCPrice(InsuranceApplication insuranceApplication) {
        double amount = 1500;
        InsuranceHistory ocHistory =  insuranceApplication.getPersons().get(0).getInsuranceHistory();
        double historyDiscount =  ((ocHistory.getHistory5YearsCountOc() - ocHistory.getLast2YearsDamagesCountOc() - ocHistory.getLast5YearsDamagesCountOc()) * 0.1);
        if(historyDiscount < 0 ) {
            historyDiscount = historyDiscount * -1;
            historyDiscount++;
        }else {
            historyDiscount = 1 - historyDiscount;
        }

        amount = amount * historyDiscount;

        int personAge = Period.between(insuranceApplication.getPersons().get(0).getDayOfBirth(), LocalDate.now()).getYears();
        int drivingLicenceHistory = Period.between(insuranceApplication.getPersons().get(0).getDrivingLicenseIssueDate(), LocalDate.now()).getYears();

        amount = personAge > 30 ? amount*0.85 : amount * 1.15;
        amount = drivingLicenceHistory > 6 ? amount*0.87 : amount * 1.13;

        return (int) amount;
    }

    private int getACPrice(InsuranceApplication insuranceApplication) {
        double amount = Integer.valueOf(insuranceApplication.getVehicle().getVehicleValue()) * 0.15;
        InsuranceHistory acHistory =  insuranceApplication.getPersons().get(0).getInsuranceHistory();
        double historyDiscount =  ((acHistory.getHistory5YearsCountAc() - acHistory.getLast2YearsDamagesCountAc() - acHistory.getLast5YearsDamagesCountAc()) * 0.1);
        if(historyDiscount < 0 ) {
            historyDiscount = historyDiscount * -1;
            historyDiscount++;
        }else {
            historyDiscount = 1 - historyDiscount;
        }

        amount = amount * historyDiscount;

        int personAge = Period.between(insuranceApplication.getPersons().get(0).getDayOfBirth(), LocalDate.now()).getYears();
        int drivingLicenceHistory = Period.between(insuranceApplication.getPersons().get(0).getDrivingLicenseIssueDate(), LocalDate.now()).getYears();

        amount = personAge > 30 ? amount*0.87 : amount * 1.13;
        amount = drivingLicenceHistory > 6 ? amount*0.91 : amount * 1.09;

        return (int) amount;
    }



    private PremiumDto getPremiumByPrice(int price) {
        PremiumDto premium = new PremiumDto();

        premium.getOne().add(price);
        premium.setTwo(getInstallmentValue(price, 2));
        premium.setFour(getInstallmentValue(price, 4));
        premium.setTwelve(getInstallmentValue(price, 12));

        return premium;

    }

    private List<Integer> getInstallmentValue(int price, int installmentAmount) {
        List<Integer> installments = new ArrayList<>();
        int amount = price / installmentAmount;

        for(int i = 0; i < installmentAmount; i++) {
            installments.add(amount);
        }


        if(amount * installmentAmount != price) {
            int rest = price - ( amount * installmentAmount);
            installments.set(0, amount + rest);
        }

        return installments;
    }
}
