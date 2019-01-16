package workerboard.evens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import workerboard.model.Experience;
import workerboard.model.InsuranceApplication;
import workerboard.model.ToDoCard;

@Component
public class EventProducer {

    private ApplicationEventPublisher publisher;

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

    public void createToDoNewEvent(ToDoCard todo) {
        publisher.publishEvent(new ToDoNewEvent(todo));
    }

    public void createNotificationEvent(TypeNotification message, Long userId) {
        publisher.publishEvent(new NotificationEvent(message.getMessage(), userId));
    }

    public void createLevelUpEvent(Experience experience) {
        publisher.publishEvent(new LevelUpEvent(experience));
    }


}
