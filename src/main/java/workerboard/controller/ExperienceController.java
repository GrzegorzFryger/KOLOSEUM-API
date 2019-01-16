package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.NotFound;
import workerboard.model.Experience;
import workerboard.serivce.ExperienceService;

@RestController
@CrossOrigin
@RequestMapping("/api/experience")
public class ExperienceController {

   private ExperienceService experienceService;

   @Autowired
   public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }


    @GetMapping("/{userId}")
    ResponseEntity<Experience> getInsuranceApplicationById(@PathVariable("userId") Long id ) throws NotFound {
        return ResponseEntity.ok(experienceService.getExperienceByUserId(id));

    }


    @PutMapping
    ResponseEntity<Experience> updateExperienceState(@RequestBody() Experience experience) throws NotFound {
       return ResponseEntity.ok(experienceService.updateExperienceState(experience));
    }



}
