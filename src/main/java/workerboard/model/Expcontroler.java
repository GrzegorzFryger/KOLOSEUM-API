package workerboard.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workerboard.repository.CustomRepository.SortType;
import workerboard.repository.ExperienceRepository;
import workerboard.serivce.ExperienceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/exp")
public class Expcontroler {

    private ExperienceService experienceService;
    private ExperienceRepository experienceRepository;

    @Autowired
    public Expcontroler(ExperienceService experienceService, ExperienceRepository experienceRepository) {
        this.experienceService = experienceService;
        this.experienceRepository = experienceRepository;
    }

    @Autowired


    @GetMapping
   public ResponseEntity<?> getexp(){

        List<String> a = new ArrayList<>();

        a.add("expTotalEarned");
        a.add("firstName");




        //return ResponseEntity.ok(experienceRepository.getExperienceScoreboard(SortType.DESC));
        return ResponseEntity.ok(experienceRepository.getPoliciesScoreboard( LocalDate.of(2018,12,12),
                LocalDate.of(2018,12,15), SortType.DESC));

    }
}
