package workerboard.evens;

import workerboard.model.InsuranceApplication;

public class InsuranceNewEvent extends AbstractEvent<InsuranceApplication> {


    public InsuranceNewEvent(InsuranceApplication insurance) {
        super(insurance);
    }
}
