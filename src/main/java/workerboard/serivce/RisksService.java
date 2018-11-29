package workerboard.serivce;

import org.springframework.stereotype.Service;
import workerboard.model.InsuranceApplication;
import workerboard.model.InsuranceHistory;
import workerboard.model.dto.PremiumDto;
import workerboard.model.dto.RiskDto;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class RisksService {


    List<RiskDto> getAvailableRisks(InsuranceApplication insuranceApplication) {
        List<RiskDto> risks = new ArrayList<>();
        risks.add(new RiskDto("OC", 6000000, getPremiumByPrice(getOCPrice(insuranceApplication)), "Ubezpieczenia komunikacyjne OC"));
        risks.add(new RiskDto("AC", Double.valueOf(insuranceApplication.getVehicle().getVehicleValue()), getPremiumByPrice(getACPrice(insuranceApplication)), "Auto Casco"));
        risks.add(new RiskDto("NNW Basic", 30000, getPremiumByPrice(150), "Następstwo Nieszczęśliwych Wypadków"));
        risks.add(new RiskDto("NNW Premium", 50000, getPremiumByPrice(240), "Następstwo Nieszczęśliwych Wypadków"));
        risks.add(new RiskDto("Assistance Basic", 15000, getPremiumByPrice(160), "Car Assistance - Wariant Podstawowy"));
        risks.add(new RiskDto("Assistance Premium", 15000, getPremiumByPrice(220), "Car Assistance - Wariant Rozszerzony"));
        risks.add(new RiskDto("Szyby", 5000, getPremiumByPrice(120), "Ubezpieczenie Szyb"));


        return risks;

    }

    private int getOCPrice(InsuranceApplication insuranceApplication) {
        double amount = 1500;
        InsuranceHistory ocHistory = insuranceApplication.getPersons().get(0).getInsuranceHistory();
        double historyDiscount = ((ocHistory.getHistory5YearsCountOc() - ocHistory.getLast2YearsDamagesCountOc() - ocHistory.getLast5YearsDamagesCountOc()) * 0.1);
        if (historyDiscount < 0) {
            historyDiscount = historyDiscount * -1;
            historyDiscount++;
        } else {
            historyDiscount = 1 - historyDiscount;
        }

        amount = amount * historyDiscount;

        int personAge = Period.between(insuranceApplication.getPersons().get(0).getDayOfBirth(), LocalDate.now()).getYears();
        int drivingLicenceHistory = Period.between(insuranceApplication.getPersons().get(0).getDrivingLicenseIssueDate(), LocalDate.now()).getYears();

        amount = personAge > 30 ? amount * 0.85 : amount * 1.15;
        amount = drivingLicenceHistory > 6 ? amount * 0.87 : amount * 1.13;

        return (int) amount;
    }

    private int getACPrice(InsuranceApplication insuranceApplication) {
        double amount = Integer.valueOf(insuranceApplication.getVehicle().getVehicleValue()) * 0.15;
        InsuranceHistory acHistory = insuranceApplication.getPersons().get(0).getInsuranceHistory();
        double historyDiscount = ((acHistory.getHistory5YearsCountAc() - acHistory.getLast2YearsDamagesCountAc() - acHistory.getLast5YearsDamagesCountAc()) * 0.1);
        if (historyDiscount < 0) {
            historyDiscount = historyDiscount * -1;
            historyDiscount++;
        } else {
            historyDiscount = 1 - historyDiscount;
        }

        amount = amount * historyDiscount;

        int personAge = Period.between(insuranceApplication.getPersons().get(0).getDayOfBirth(), LocalDate.now()).getYears();
        int drivingLicenceHistory = Period.between(insuranceApplication.getPersons().get(0).getDrivingLicenseIssueDate(), LocalDate.now()).getYears();

        amount = personAge > 30 ? amount * 0.87 : amount * 1.13;
        amount = drivingLicenceHistory > 6 ? amount * 0.91 : amount * 1.09;

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
        price = (int) (price * installmentIncrease(installmentAmount));
        int amount = price / installmentAmount;

        for (int i = 0; i < installmentAmount; i++) {
            installments.add(amount);
        }


        if (amount * installmentAmount != price) {
            int rest = price - (amount * installmentAmount);
            installments.set(0, amount + rest);
        }

        return installments;
    }

    private double installmentIncrease(int installmentAmount) {
        switch (installmentAmount) {
            case 1:
                return 1;
            case 2:
                return 1.2;
            case 4:
                return 1.4;
        }
        return 1.6;
    }
}
