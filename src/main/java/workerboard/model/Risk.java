package workerboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Risk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double sum;
    private Premium premiumList;

    public Risk() {
    }


    public Risk(String name, double sum, Premium premiumList) {
        this.name = name;
        this.sum = sum;
        this.premiumList = premiumList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSum() {
        return sum;
    }

    public Premium getPremiumList() {
        return premiumList;
    }
}
