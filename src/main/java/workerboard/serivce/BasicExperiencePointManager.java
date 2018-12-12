package workerboard.serivce;

import io.jsonwebtoken.lang.Assert;
import workerboard.model.Experience;


public class BasicExperiencePointManager implements ExperiencePointManager {

    private Experience experience;
    private int converterPoint;

    public BasicExperiencePointManager() {
    }

    @Override
    public Experience addPoint(Experience experience, Long experiencePoint) {
        Assert.notNull(experience, "Object must not be null");
        Assert.notNull(experiencePoint, "Object must not be null");

        this.setExperience(experience);
        this.addExperiencePoint(experiencePoint / converterPoint);
        this.generateLevelValue();
        this.generatePointToNextLevel();

        return experience;
    }



    protected void generateLevelValue(){
        this.experience.setLevel(Math.round(
                Math.log10(experience.getExpTotalEarned())
        ))  ;

    }

    protected void generatePointToNextLevel(){

        experience.setExpToNextLevel(Math.round(
                Math.pow(10, experience.getLevel() + 1) - experience.getExpTotalEarned())
        );

    }

    protected void addExperiencePoint(Long exp){
        this.experience.setExpTotalEarned(this.experience.getExpTotalEarned() + exp );
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
