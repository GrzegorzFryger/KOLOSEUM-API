package workerboard.model;

public class ExperienceScore extends Score {

    private long expTotalEarned;
    private long level;
    private long expToNextLevel;
    private int attack;
    private int defence;
    private int knowledge;

    public ExperienceScore(long id, long expToNextLevel, long level, long expTotalEarned,
                           String firstName, String lastName, int attack, int defence, int knowledge) {
        super(id, firstName, lastName);
        this.expTotalEarned = expTotalEarned;
        this.level = level;
        this.expToNextLevel = expToNextLevel;
        this.attack = attack;
        this.defence = defence;
        this.knowledge = knowledge;
    }
}
