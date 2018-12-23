package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.NotFound;
import workerboard.exception.WrongTypeArguments;
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


    @PostMapping("/pointattributes")
    public ResponseEntity<?> updatePointAttributes(@RequestBody Experience experience) throws NotFound, WrongTypeArguments {


       return ResponseEntity.ok(experienceService.updatePointAttributes(experience));

    }


}
