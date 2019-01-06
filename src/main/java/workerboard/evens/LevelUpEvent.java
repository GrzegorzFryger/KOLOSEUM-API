package workerboard.evens;

import workerboard.model.Experience;

public class LevelUpEvent extends AbstractEvent<Experience> {


    public LevelUpEvent(Experience experience) {
        super(experience);
    }
}
