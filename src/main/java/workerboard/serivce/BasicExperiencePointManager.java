package workerboard.serivce;

import workerboard.model.Experience;


public class BasicExperiencePointManager implements ExperiencePointManager {





    public Experience countExperience(Experience experience, Long policyValue){
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



































