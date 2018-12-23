package workerboard.model;

public class ExperienceScore extends Score {

    private long expTotalEarned;
    private int attack;
    private int defence;
    private int knowledge;

    public ExperienceScore(long id,long expTotalEarned, String firstName, String lastName,
                           int attack, int defence, int knowledge) {
        super(id, firstName, lastName);
        this.expTotalEarned = expTotalEarned;
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
