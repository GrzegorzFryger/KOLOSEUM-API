package workerboard.evens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import workerboard.model.InsuranceApplication;

@Component
public class EventProducer {

    private final ApplicationEventPublisher publisher;

    @Autowired
    public EventProducer(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void createInsuranceNewEvent(InsuranceApplication application) {
        publisher.publishEvent(new InsuranceNewEvent(application));
    }

    public void createInsuranceUpdateEvent(InsuranceApplication application) {
        publisher.publishEvent(new InsuranceUpdateEvent(application));
    }






}
