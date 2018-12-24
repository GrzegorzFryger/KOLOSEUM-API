package workerboard.evens;

import workerboard.model.InsuranceApplication;

public class InsuranceUpdateEvent extends InsuranceEvent
{
    public InsuranceUpdateEvent(InsuranceApplication insurance) {
        super(insurance);
    }
}
