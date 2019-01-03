package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.model.Experience;
import workerboard.model.InsuranceApplication;
import workerboard.model.ToDoCard;
import workerboard.model.enums.ToDoCardState;
import workerboard.repository.ExperienceRepository;

import java.util.stream.Collectors;

@Service
public class ExperienceService {

   // private static final Logger logger = LoggerFactory.getLogger(ExperienceService.class);


    private ExperiencePointManager experiencePointManager;
    private ExperienceRepository repository;

    @Autowired
    public ExperienceService(ExperiencePointManager experiencePointManager, ExperienceRepository repository) {
        this.experiencePointManager = experiencePointManager;
        this.repository = repository;
    }

    public void setExperiencePoint(InsuranceApplication insurance ){

        Experience experience = repository.findById(insurance.getSeller().getId()).get();
        experience = experiencePointManager.countExperience(
                experience,
                insurance.getTotalPolicyValue().longValue());

       repository.save(experience);
    }

    public void setExperiencePoint(ToDoCard toDoCard){

       if( toDoCard.getToDoCardHistories().stream().map(data -> data.getState() == ToDoCardState.DONE)
               .collect(Collectors.toList()).size() == 1 ){

           Experience experience = repository.findById(toDoCard.getUser().getId()).get();
           experience = experiencePointManager.countExperience(
                   experience,
                   200L);
           repository.save(experience);
       }

    }

    public void subtractExperiencePoint(InsuranceApplication insurance ){

        Experience experience = repository.findById(insurance.getSeller().getId()).get();
        experience = experiencePointManager.countExperience(
                experience,
                (insurance.getTotalPolicyValue().longValue()) * -1 );

       repository.save(experience);
    }
    

}
