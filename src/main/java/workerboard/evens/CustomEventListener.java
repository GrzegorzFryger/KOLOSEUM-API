package workerboard.evens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import workerboard.serivce.ExperienceService;
import workerboard.socket.ScoreboardSocketPublisher;

@Service
public class CustomEventListener {


    private ExperienceService service;
    private ScoreboardSocketPublisher publisher;


    @Autowired
    public CustomEventListener(ExperienceService service, ScoreboardSocketPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void handle(InsuranceNewEvent applicationCreateEvent) {

        System.out.print(applicationCreateEvent.getEventObject());

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
}
