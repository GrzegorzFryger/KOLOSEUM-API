package workerboard.serivce;

import workerboard.model.Experience;

public interface ExperiencePointManager {

    Experience countExperience(Experience experience, Long policyValue, Long userID);
}

