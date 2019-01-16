package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.evens.EventProducer;
import workerboard.evens.TypeNotification;
import workerboard.exception.NotFound;
import workerboard.model.ApplicationUser;
import workerboard.model.Experience;
import workerboard.model.InsuranceApplication;
import workerboard.model.ToDoCard;
import workerboard.model.enums.ToDoCardState;
import workerboard.repository.ApplicationUserCustomRepository;
import workerboard.repository.ExperienceRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExperienceService {

    // private static final Logger logger = LoggerFactory.getLogger(ExperienceService.class);


    private ExperiencePointManager experiencePointManager;
    private ExperienceRepository repository;
    private ApplicationUserCustomRepository applicationUserRepository;
    private EventProducer eventProducer;

    @Autowired
    public ExperienceService(ExperiencePointManager experiencePointManager, ExperienceRepository repository, ApplicationUserCustomRepository applicationUserRepository, EventProducer eventProducer) {
        this.experiencePointManager = experiencePointManager;
        this.repository = repository;
        this.applicationUserRepository = applicationUserRepository;
        this.eventProducer = eventProducer;
    }

    public void setExperiencePoint(InsuranceApplication insurance) {


        Experience experienceTemp = repository.findById(insurance.getSeller().getId()).get();
        Experience experience = repository.findById(insurance.getSeller().getId()).get();
        experience = experiencePointManager.countExperience(
                experience,
                insurance.getTotalPolicyValue().longValue(), insurance.getSeller().getId());

        if(experience.getLevel() != experienceTemp.getLevel()) {
            this.eventProducer.createLevelUpEvent(experience);
            eventProducer.createNotificationEvent(TypeNotification.NEXT_LEVEL,insurance.getSeller().getId());
        }

        repository.save(experience);
    }

    public void setExperiencePoint(ToDoCard toDoCard) {

        if (toDoCard.getToDoCardHistories().stream().map(data -> data.getState() == ToDoCardState.DONE)
                .collect(Collectors.toList()).size() == 1) {

            Experience experience = repository.findById(toDoCard.getUser().getId()).get();
            experience = experiencePointManager.countExperience(
                    experience,
                    200L,
                    toDoCard.getUser().getId());
            repository.save(experience);
        }

    }

    public void subtractExperiencePoint(InsuranceApplication insurance) {

        Experience experience = repository.findById(insurance.getSeller().getId()).get();
        experience = experiencePointManager.countExperience(
                experience,
                (insurance.getTotalPolicyValue().longValue()) * -1, insurance.getSeller().getId());

        repository.save(experience);
    }


    public Experience getExperienceByUserId(Long id) throws NotFound {
        Optional<ApplicationUser> applicationUserOptional = applicationUserRepository.findById(id);

        if (applicationUserOptional.isPresent()) {
            ApplicationUser applicationUser = applicationUserOptional.get();
            return applicationUser.getExperience();
        }

        throw  new NotFound("User with ID: " + id + " not found");

    }

    public Experience updateExperienceState(Experience experience) throws NotFound {
        Optional<Experience> experienceOptional = repository.findById(experience.getId());

        if(experienceOptional.isPresent()) {
            Experience experienceFromDb = experienceOptional.get();
            experienceFromDb = experience;
            return repository.save(experienceFromDb);
        }

        throw new NotFound("Experience with ID: " + experience.getId() + " not found");
    }
}
