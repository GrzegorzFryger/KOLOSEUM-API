package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workerboard.model.ExperienceScore;
import workerboard.model.PoliciesScore;
import workerboard.repository.CustomRepository.ScoreboardCustomRepository;
import workerboard.repository.CustomRepository.SortType;

import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/temp")
public class ScoreboardController {

    private ScoreboardCustomRepository scoreboardCustomRepository;

    @Autowired
    public ScoreboardController(ScoreboardCustomRepository scoreboardCustomRepository) {
        this.scoreboardCustomRepository = scoreboardCustomRepository;
    }


    @GetMapping("/experience")
    public ResponseEntity<?> getExperienceScore(){

        return ResponseEntity.ok(updateExperienceScore());

    }

    @GetMapping("/policies")
    public ResponseEntity<?> getPoliciesScore(){

        return ResponseEntity.ok(updatePoliciesScore());

    }

    private List<ExperienceScore> updateExperienceScore(){
        return this.scoreboardCustomRepository.getExperienceScoreboard(SortType.DESC);
    }

    private List<PoliciesScore> updatePoliciesScore(){

        LocalDate currentDate = LocalDate.now();

        return this.scoreboardCustomRepository.getPoliciesScoreboard(
                LocalDate.of(currentDate.getYear(),currentDate.getMonth(),1),
                currentDate,
                SortType.DESC);
    }
}
