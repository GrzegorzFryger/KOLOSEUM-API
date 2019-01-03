package workerboard.model;

public class ExperienceScore extends Score {

    private long expTotalEarned;
    private long level;
    private long expToNextLevel;
    private int attack;
    private int defence;
    private int knowledge;
    private int pointsToAdd;
    private double percentToNextLvl;
    private int speedAttack;

    public ExperienceScore(long id, long expToNextLevel, long level, long expTotalEarned,
                           String firstName, String lastName, int attack, int defence, int knowledge, int pointsToAdd, double percentToNextLvl, int speedAttack) {
        super(id, firstName, lastName);
        this.expToNextLevel = expToNextLevel;
        this.level = level;
        this.expTotalEarned = expTotalEarned;
        this.attack = attack;
        this.defence = defence;
        this.knowledge = knowledge;
        this.pointsToAdd = pointsToAdd;
        this.percentToNextLvl = percentToNextLvl;
        this.speedAttack = speedAttack;
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

    public int getPointsToAdd() {
        return pointsToAdd;
    }

    public void setPointsToAdd(int pointsToAdd) {
        this.pointsToAdd = pointsToAdd;
    }

    public double getPercentToNextLvl() {
        return percentToNextLvl;
    }

    public void setPercentToNextLvl(double percentToNextLvl) {
        this.percentToNextLvl = percentToNextLvl;
    }

    public int getSpeedAttack() {
        return speedAttack;
    }

    public void setSpeedAttack(int speedAttack) {
        this.speedAttack = speedAttack;
    }
}
