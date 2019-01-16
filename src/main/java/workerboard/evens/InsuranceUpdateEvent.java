package workerboard.evens;

import workerboard.model.InsuranceApplication;

public class InsuranceUpdateEvent extends AbstractEvent<InsuranceApplication>
{
    public InsuranceUpdateEvent(InsuranceApplication insurance) {
        super(insurance);
    }
}
