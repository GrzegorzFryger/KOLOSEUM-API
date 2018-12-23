package workerboard.model;

public class PoliciesScore extends Score {

   private long totaloliciesValue;

    public PoliciesScore(long id, String firstName, String lastName, long totaloliciesValue) {
        super(id, firstName, lastName);
        this.totaloliciesValue = totaloliciesValue;
    }

    public long getTotaloliciesValue() {
        return totaloliciesValue;
    }

    public void setTotaloliciesValue(long totaloliciesValue) {
        this.totaloliciesValue = totaloliciesValue;
    }
}
