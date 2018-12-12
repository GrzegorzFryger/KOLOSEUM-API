package workerboard.serivce;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    public void setPoint(InsuranceApplication insurance ){

        Experience experience = repository.findById(insurance.getSeller().getId()).get();
        experience = experiencePointManager.addPoint(
                experience,
                insurance.getTotalPolicyValue().longValue());

       repository.save(experience);


    }


    public Experience getExperienceById(Long id)
    {
        return null;//repository.findById(id);
    }

}
