package workerboard.evens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import workerboard.model.ApplicationUser;
import workerboard.model.Experience;
import workerboard.serivce.ExperienceService;

@Component
public class CustomEventListener {


    private ExperienceService service;
    private MessageSendingOperations messagingTemplate;


    public CustomEventListener(ExperienceService service, MessageSendingOperations messagingTemplate) {
        this.service = service;
        this.messagingTemplate = messagingTemplate;
    }

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void handle(InsuranceEvent applicationCreateEvent) {

//

        service.setPoint(applicationCreateEvent.getInsurance());



       // this.messagingTemplate.convertAndSend();



        System.out.print("\n"+ " Listner Watek : "+  Thread.currentThread().getId()+ " Nazwa : "+Thread.currentThread()
                .getName() + applicationCreateEvent.getInsurance()+ "\n");


    }
}
