package workerboard.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationPublisher {

    private final SimpMessagingTemplate simpMessagingTemplate;


    @Autowired
    public NotificationPublisher(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;

    }


    public void publishData(String message, Long userId){

        this.simpMessagingTemplate.convertAndSend("/score/notification/" + userId, message);
    }


}
