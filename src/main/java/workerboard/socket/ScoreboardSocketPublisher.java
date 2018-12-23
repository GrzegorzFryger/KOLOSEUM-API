package workerboard.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import workerboard.model.ExperienceScore;
import workerboard.model.PoliciesScore;
import workerboard.repository.CustomRepository.ScoreboardCustomRepository;
import workerboard.repository.CustomRepository.SortType;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ScoreboardSocketPublisher {



    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ScoreboardCustomRepository repository;

    public ScoreboardSocketPublisher(SimpMessagingTemplate simpMessagingTemplate, ScoreboardCustomRepository repository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.repository = repository;
    }

    @Autowired


    @Async("threadPoolTaskExecutor")
    public void publishData(){

        this.simpMessagingTemplate.convertAndSend("/score/experience", this.updateExperienceScore());
        this.simpMessagingTemplate.convertAndSend("/score/policies", this.updateExperienceScore());
        System.out.print(this.updateExperienceScore());

    }

    private List<ExperienceScore> updateExperienceScore(){
        return this.repository.getExperienceScoreboard(SortType.DESC);
    }

    private List<PoliciesScore> updatePoliciesScore(){

        LocalDate currentDate = LocalDate.now();

        return this.repository.getPoliciesScoreboard(
                LocalDate.of(currentDate.getYear(),currentDate.getMonth(),1),
                currentDate,
                SortType.DESC);
    }

}
