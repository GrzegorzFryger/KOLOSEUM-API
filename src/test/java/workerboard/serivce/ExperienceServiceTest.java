package workerboard.serivce;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import workerboard.exception.NotFound;
import workerboard.exception.WrongTypeArguments;
import workerboard.model.Experience;
import workerboard.repository.ExperienceRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ExperienceServiceTest {

    @Mock
    ExperienceRepository repository;
    @Autowired
    ExperiencePointManager experiencePointManager;

    ExperienceService experienceService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.experienceService = new ExperienceService(experiencePointManager,repository);
    }

    @Test
    public void setExperiencePoint() {

    }

    @Test
    public void updatePointAttributes() throws NotFound, WrongTypeArguments {
        Experience experience = new Experience();

        experience.setId(Long.valueOf(1));
        experience.setPointsToAdd(4);
        experience.setAttack(0);
        experience.setDefence(0);
        experience.setKnowledge(0);

        Experience experienceToUpadate = new Experience();

        experienceToUpadate.setId(Long.valueOf(1));
        experienceToUpadate.setPointsToAdd(0);
        experienceToUpadate.setAttack(1);
        experienceToUpadate.setDefence(2);
        experienceToUpadate.setKnowledge(1);

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(experience));
//

        Experience returned = new Experience();

        when(repository.save(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    return args[0];
                });

        Experience experience1 = experienceService.updatePointAttributes(experienceToUpadate);

        System.out.print(experience1);
        Assert.assertEquals(experienceToUpadate.getAttack(),experience1.getAttack());




    }

    @Test
    public void getExperienceById() {



    }
}