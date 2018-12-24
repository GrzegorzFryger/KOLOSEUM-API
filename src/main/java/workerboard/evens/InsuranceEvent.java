package workerboard.evens;

import workerboard.model.InsuranceApplication;

public class InsuranceEvent {

    private InsuranceApplication insurance;

    public InsuranceEvent(InsuranceApplication insurance) {
        this.insurance = insurance;
    }


    public InsuranceApplication getInsurance() {
        return insurance;
    }
}
