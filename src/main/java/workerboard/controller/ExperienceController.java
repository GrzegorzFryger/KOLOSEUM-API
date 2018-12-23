package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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



}
