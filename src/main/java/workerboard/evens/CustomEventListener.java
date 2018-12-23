package workerboard.evens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import workerboard.serivce.ExperienceService;
import workerboard.socket.ScoreboardSocketPublisher;

@Component
public class CustomEventListener {


    private ExperienceService service;
    private MessageSendingOperations messagingTemplate;
    private ScoreboardSocketPublisher publisher;


    @Autowired
    public CustomEventListener(ExperienceService service, MessageSendingOperations messagingTemplate, ScoreboardSocketPublisher publisher) {
        this.service = service;
        this.messagingTemplate = messagingTemplate;
        this.publisher = publisher;
    }

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void handle(InsuranceEvent applicationCreateEvent) {

        service.setPoint(applicationCreateEvent.getInsurance());
        publisher.publishData();
    }
}
