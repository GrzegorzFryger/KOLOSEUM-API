package workerboard.serivce;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workerboard.model.Experience;

import static org.junit.Assert.*;

public class BasicExperiencePointManagerTest {

    BasicExperiencePointManager pointManager;
    Experience experience;

    @Before
    public void setUp() throws Exception {

        pointManager = new BasicExperiencePointManager();
        pointManager.setConverterPoint(100);
        experience = new Experience();
    }

    @Test
    public void check_level_value() {

        pointManager.addPoint(experience,Long.valueOf(1000));
        Assert.assertEquals(1,experience.getLevel());


        pointManager.addPoint(experience,Long.valueOf(1000));
        Assert.assertEquals(1,experience.getLevel());

        pointManager.addPoint(experience,Long.valueOf(8000));
        Assert.assertEquals(2,experience.getLevel());

    }

    @Test
    public void check_exp_value() {

        //when
        pointManager.addPoint(experience,Long.valueOf(1000));
        //then
        Assert.assertEquals(10,experience.getExpTotalEarned());

        //when
        pointManager.addPoint(experience,Long.valueOf(1000));
        //then
        Assert.assertEquals(20,experience.getExpTotalEarned());

        //when
        pointManager.addPoint(experience,Long.valueOf(8000));
        //then
        Assert.assertEquals(100,experience.getExpTotalEarned());


    }

    @Test
    public void check_exp_to_next_level() {

        pointManager.addPoint(experience,Long.valueOf(1000));

        Assert.assertEquals(90,experience.getExpToNextLevel());




    }


}