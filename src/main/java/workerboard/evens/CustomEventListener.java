package workerboard.evens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import workerboard.serivce.ExperienceService;
import workerboard.socket.NotificationPublisher;
import workerboard.socket.ScoreboardSocketPublisher;

@Service
public class CustomEventListener {


    private ExperienceService service;
    private ScoreboardSocketPublisher publisher;
    private NotificationPublisher notificationPublisher;

    @Autowired
    public CustomEventListener(ExperienceService service, ScoreboardSocketPublisher publisher, NotificationPublisher notificationPublisher) {
        this.service = service;
        this.publisher = publisher;
        this.notificationPublisher = notificationPublisher;
    }

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void handle(InsuranceNewEvent applicationCreateEvent) {

        service.setExperiencePoint(applicationCreateEvent.getEventObject());
        publisher.publishData();
    }

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void  handle(InsuranceUpdateEvent applicationCreateEvent) {

        service.subtractExperiencePoint(applicationCreateEvent.getEventObject());
        publisher.publishData();
    }

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void  handle(ToDoNewEvent toDoNewEvent) {
        
        service.setExperiencePoint(toDoNewEvent.getEventObject());
        publisher.publishData();
    }

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void  handle(NotificationEvent notificationEvent) {


        notificationPublisher.publishData(notificationEvent.getEventObject(),
                notificationEvent.getUserId());
    }


}
