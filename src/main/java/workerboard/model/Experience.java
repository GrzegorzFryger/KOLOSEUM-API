package workerboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long level;
    private long expTotalEarned;
    private long expToNextLevel;
    private double percentToNextLevel;
    private int attack;
    private int defence;
    private int knowledge;
    private int speedAttack;
    private int pointsToAdd;

    public Experience() {
        this.level = 1;
        this.expToNextLevel = 1000;
        this.expTotalEarned = 0;
    }

    public Experience(long level, long expTotalEarned, long expToNextLevel, double percentToNextLevel, int attack, int defence, int knowledge, int speedAttack, int pointsToAdd) {
        this.level = level;
        this.expTotalEarned = expTotalEarned;
        this.expToNextLevel = expToNextLevel;
        this.percentToNextLevel = percentToNextLevel;
        this.attack = attack;
        this.defence = defence;
        this.knowledge = knowledge;
        this.speedAttack = speedAttack;
        this.pointsToAdd = pointsToAdd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getExpTotalEarned() {
        return expTotalEarned;
    }

    public void setExpTotalEarned(long expTotalEarned) {
        this.expTotalEarned = expTotalEarned;
    }

    public long getExpToNextLevel() {
        return expToNextLevel;
    }

    public void setExpToNextLevel(long expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public double getPercentToNextLevel() {
        return percentToNextLevel;
    }

    public void setPercentToNextLevel(double percentToNextLevel) {
        this.percentToNextLevel = percentToNextLevel;
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

    public int getSpeedAttack() {
        return speedAttack;
    }

    public void setSpeedAttack(int speedAttack) {
        this.speedAttack = speedAttack;
    }

    public int getPointsToAdd() {
        return pointsToAdd;
    }

    public void setPointsToAdd(int pointsToAdd) {
        this.pointsToAdd = pointsToAdd;
    }
}
