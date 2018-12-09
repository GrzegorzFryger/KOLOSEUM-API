package workerboard.evens;

import org.springframework.context.ApplicationEvent;
import workerboard.model.InsuranceApplication;

public class InsuranceEvent extends ApplicationEvent {

    private InsuranceApplication insurance;


    public InsuranceEvent(Object source, InsuranceApplication insurance) {
        super(source);
        this.insurance = insurance;

    }

    public InsuranceApplication getInsurance() {
        return insurance;
    }
}
