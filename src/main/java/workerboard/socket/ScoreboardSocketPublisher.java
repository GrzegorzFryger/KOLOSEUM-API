package workerboard.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import workerboard.model.Experience;
import workerboard.repository.ExperienceRepository;

@Controller
public class ScoreboardSocketPublisher {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ExperienceRepository experienceRepository;

    @Autowired

    public ScoreboardSocketPublisher(SimpMessagingTemplate simpMessagingTemplate, ExperienceRepository experienceRepository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.experienceRepository = experienceRepository;
    }

    @Async("threadPoolTaskExecutor")
    public void sentMessage(){

        this.simpMessagingTemplate.convertAndSend("/allscores", "sad");

    }
}
