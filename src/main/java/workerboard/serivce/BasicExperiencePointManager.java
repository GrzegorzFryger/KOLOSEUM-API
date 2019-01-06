package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.evens.EventProducer;
import workerboard.model.Experience;

@Service
public class BasicExperiencePointManager implements ExperiencePointManager {


    private EventProducer eventProducer;

    @Autowired
    public BasicExperiencePointManager(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    public BasicExperiencePointManager() {
    }

    public Experience countExperience(Experience experience, Long policyValue, Long userId){
        experience.setExpTotalEarned(experience.getExpTotalEarned() + policyValue);


        while(experience.getExpTotalEarned() <= countExpToNextLvl(experience.getLevel()) ) {
            experience.setLevel(experience.getLevel() - 1);
            experience.setPointsToAdd(experience.getPointsToAdd() - 4);
            experience.setExpToNextLevel(countExpToNextLvl(experience.getLevel() - 1));
        }

        while ( isNextLvl(experience)) {
            experience.setLevel(experience.getLevel() + 1);
            experience.setPointsToAdd(experience.getPointsToAdd() + 4);
            experience.setExpToNextLevel(countExpToNextLvl(experience.getLevel() + 1));
//            this.eventProducer.createLevelUpEvent(experience);
//            eventProducer.createNotificationEvent(TypeNotification.NEXT_LEVEL,userId);
        }

        experience.setPercentToNextLvl(experienceToNextLvl(experience));

        return experience;
    }


    private boolean isNextLvl(Experience experience) {
        return experience.getExpTotalEarned() >= experience.getExpToNextLevel();
    }

    private int countExpToNextLvl(long lvl) {
        double exponent = 1.5;
        double baseXP = 1000;
        return (int) Math.floor(baseXP * (Math.pow(lvl, exponent)));
    }

    private double experienceToNextLvl(Experience experience) {
        int expNeededToCurrentLvl = 0;
        if ( experience.getLevel() > 1) {
            expNeededToCurrentLvl = countExpToNextLvl(experience.getLevel());
        }

        double a = experience.getExpTotalEarned() - expNeededToCurrentLvl;
        double b = experience.getExpToNextLevel() - expNeededToCurrentLvl;


        double result = (a / b);

        return round(result, 2);

    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }



}



































