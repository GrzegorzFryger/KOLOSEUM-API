package workerboard.serivce;

import io.jsonwebtoken.lang.Assert;
import workerboard.model.Experience;


public class BasicExperiencePointManager implements ExperiencePointManager {

    private Experience experience;
    private int converterPoint;
    private int pointToAddForExperience;

    public BasicExperiencePointManager() {
        pointToAddForExperience = 4;
    }

    @Override
    public Experience addExperiencePoint(Experience experience, Long experiencePoint) {
        Assert.notNull(experience, "Object must not be null");
        Assert.notNull(experiencePoint, "Object must not be null");

        this.setExperience(experience);
        this.addExperiencePoint(experiencePoint / converterPoint);
        this.generateLevelValue();
        this.generatePointToNextLevel();

        return experience;
    }



    protected void generateLevelValue(){

        long tempLevel = Math.round(
                Math.log10(experience.getExpTotalEarned()));

        if(tempLevel > this.experience.getLevel()) {

            for(int i = 0;i <= tempLevel - this.experience.getLevel(); i++ ){
                generatePointForAttributes();
            }
            this.experience.setLevel(tempLevel);
        }
    }

    protected void generatePointToNextLevel(){


        experience.setExpToNextLevel(Math.round(
                Math.pow(10, experience.getLevel() + 1) - experience.getExpTotalEarned())
        );
    }

    protected void addExperiencePoint(Long exp){
        this.experience.setExpTotalEarned(this.experience.getExpTotalEarned() + exp );
    }

    protected void generatePointForAttributes() {
        this.experience.setPointsToAdd(experience.getPointsToAdd() + pointToAddForExperience);
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public int getConverterPoint() {
        return converterPoint;
    }

    public void setConverterPoint(int converterPoint) {
        this.converterPoint = converterPoint;
    }
}
