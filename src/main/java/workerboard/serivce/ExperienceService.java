package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.exception.NotFound;
import workerboard.exception.WrongTypeArguments;
import workerboard.model.Experience;
import workerboard.model.InsuranceApplication;
import workerboard.repository.ExperienceRepository;

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
        experience = experiencePointManager.addExperiencePoint(
                experience,
                insurance.getTotalPolicyValue().longValue());

       repository.save(experience);
    }

    public Experience updatePointAttributes(Experience experience) throws NotFound, WrongTypeArguments {

        Experience temp = this.getExperienceById(experience.getId());

       if( (experience.getAttack() + experience.getDefence() + experience.getKnowledge() + experience.getPointsToAdd() + experience.getSpeedAttack()) >
               (temp.getAttack() + temp.getDefence() + temp.getKnowledge() + temp.getPointsToAdd() + temp.getSpeedAttack() )) {
           throw new WrongTypeArguments("To much point to add");
       }else {
           temp.setAttack(experience.getAttack());
           temp.setDefence(experience.getDefence());
           temp.setKnowledge(experience.getKnowledge());
           temp.setSpeedAttack(experience.getSpeedAttack());
           temp.setPointsToAdd(temp.getPointsToAdd() -
                   (experience.getAttack() + experience.getDefence() + experience.getKnowledge() + experience.getSpeedAttack())
           );
           temp = repository.save(temp);
       }

        return temp;

    }
    public Experience getExperienceById(Long id) throws NotFound {

        return repository.findById(id)
                .orElseThrow(() ->  new NotFound("User not found by id " + id));
    }

}
