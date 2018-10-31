package workerboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InsuranceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int history5YearsCountOc;
    private int last2YearsDamagesCountOc;
    private int last5YearsDamagesCountOc;
    private int history5YearsCountAc;
    private int last2YearsDamagesCountAc;
    private int last5YearsDamagesCountAc;

    public InsuranceHistory() {
    }

    public InsuranceHistory(int history5YearsCountOc, int last2YearsDamagesCountOc, int last5YearsDamagesCountOc, int history5YearsCountAc, int last2YearsDamagesCountAc, int last5YearsDamagesCountAc) {
        this.history5YearsCountOc = history5YearsCountOc;
        this.last2YearsDamagesCountOc = last2YearsDamagesCountOc;
        this.last5YearsDamagesCountOc = last5YearsDamagesCountOc;
        this.history5YearsCountAc = history5YearsCountAc;
        this.last2YearsDamagesCountAc = last2YearsDamagesCountAc;
        this.last5YearsDamagesCountAc = last5YearsDamagesCountAc;
    }

    public Long getId() {
        return id;
    }

    public int getHistory5YearsCountOc() {
        return history5YearsCountOc;
    }

    public int getLast2YearsDamagesCountOc() {
        return last2YearsDamagesCountOc;
    }

    public int getLast5YearsDamagesCountOc() {
        return last5YearsDamagesCountOc;
    }

    public int getHistory5YearsCountAc() {
        return history5YearsCountAc;
    }

    public int getLast2YearsDamagesCountAc() {
        return last2YearsDamagesCountAc;
    }

    public int getLast5YearsDamagesCountAc() {
        return last5YearsDamagesCountAc;
    }
}
