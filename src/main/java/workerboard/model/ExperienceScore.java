package workerboard.model;

public class ExperienceScore extends Score {

    private long expTotalEarned;

    public ExperienceScore(long id, long expTotalEarned, String firstName, String lastName ) {
        super(id, firstName, lastName);
        this.expTotalEarned = expTotalEarned;
    }



    public long getExpTotalEarned() {
        return expTotalEarned;
    }

    public void setExpTotalEarned(long expTotalEarned) {
        this.expTotalEarned = expTotalEarned;
    }
}
