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

    public long getExpTotalEarned() {
        return expTotalEarned;
    }

    public void setExpTotalEarned(long expTotalEarned) {
        this.expTotalEarned = expTotalEarned;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getExpToNextLevel() {
        return expToNextLevel;
    }

    public void setExpToNextLevel(long expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }
}
