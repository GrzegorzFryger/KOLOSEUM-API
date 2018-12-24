package workerboard.serivce;

import workerboard.model.Experience;

public interface ExperiencePointManager {

    public Experience addExperiencePoint(Experience experience, Long experiencePoint);


    Experience subtractExperiencePoint(Experience experience, Long experiencePoint);
}

